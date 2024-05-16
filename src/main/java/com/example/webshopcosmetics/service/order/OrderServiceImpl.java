package com.example.webshopcosmetics.service.order;

import com.example.webshopcosmetics.dto.CustomerDTO;
import com.example.webshopcosmetics.exception.ManufacturerException;
import com.example.webshopcosmetics.exception.OrderException;
import com.example.webshopcosmetics.model.CancellationReason;
import com.example.webshopcosmetics.model.Order;
import com.example.webshopcosmetics.model.OrderStatus;
import com.example.webshopcosmetics.model.ProductOptions;
import com.example.webshopcosmetics.repository.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.EnumUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired private OrderRepository orderRepository;

    @Override
    public List<Order> getALlOrders() {
        return orderRepository.getALlOrders();
    }

    @Override
    public List<Order> getOrderByCustomerId(HttpSession session) {
        CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
        List<Order> order = orderRepository.getALlOrderByCustomerId(customerDTO.id());
        return order;
    }

    @Override
    public Page<Order> getAllOrder(int pageNo, String orderCode, int size, String statusString) {
        if (orderCode == null) {
            orderCode = "";
        } else {
            orderCode = orderCode.trim();
        }
        if (orderRepository.count() <= size) {
            pageNo = 1;
        }

        OrderStatus status = null;
        try {
            if (statusString != null && !statusString.isEmpty()) {
                status = OrderStatus.valueOf(statusString.toUpperCase());
            }
        } catch (IllegalArgumentException e) {
            // Nếu statusString không khớp với bất kỳ giá trị nào của OrderStatus, status sẽ là null
            status = null;
        }
        System.out.println("status:" + status);

        boolean allOrder = (status == null);
        System.out.println("allOrder:" + allOrder);

        Sort sort = Sort.by("orderDate").descending();
        Pageable pageable = PageRequest.of(pageNo - 1, size, sort);

        if (allOrder) {
            System.out.println("allOrder1:" + orderCode);
            return orderRepository.findByOrderCodeContainingIgnoreCase(orderCode, pageable);
        } else {
            return orderRepository.findByOrderCodeAndStatus(orderCode, status, pageable);
        }
    }

    @Override
    public List<Order> getAllTheOrdersWasAbort() {
        return orderRepository.getAllTheOrdersWasAbort(OrderStatus.CANCELLED);
    }

    @Override
    public List<Order> getAllOrdersInProcess() {
        return orderRepository.getAllOrdersInProcess(OrderStatus.PROCESSING);
    }

    @Override
    public List<Order> pickUpAllOrdersThatAreBeingDelivered() {
        return orderRepository.pickUpAllOrdersThatAreBeingDelivered(OrderStatus.SHIPPED);
    }

    @Override
    public List<Order> getAllOrdersThatHaveBeenSuccessfullyDelivered() {
        return orderRepository.getAllOrdersThatHaveBeenSuccessfullyDelivered(OrderStatus.DELIVERED);
    }

    @Override
    public void deliveryConfirmation(Long orderID) {
        try {
            Optional<Order> optionalOrder = orderRepository.findById(orderID);
            Order order = optionalOrder.orElse(null);
            if (order != null) {
                order.setStatus(OrderStatus.SHIPPED);
                orderRepository.save(order);
            }
        } catch (Exception e) {
            throw new OrderException("Lỗi khi xác nhận giao hàng", e);
        }
    }

    @Override
    public void deliveredSuccessfully(Long orderID) {
        try {
            Optional<Order> optionalOrder = orderRepository.findById(orderID);
            Order order = optionalOrder.orElse(null);
            if (order != null) {
                order.setStatus(OrderStatus.DELIVERED);
                orderRepository.save(order);
            }
        } catch (Exception e) {
            throw new OrderException("Lỗi khi giao hàng", e);
        }
    }

    @Override
    public void cancelOrder(CancellationReason cancellationReason, Long orderID, HttpSession session) {
        try {
            Optional<Order> optionalOrder = orderRepository.findById(orderID);
            Order order = optionalOrder.orElse(null);
            if (order != null) {
                order.setCancellationReason(cancellationReason);
                LocalDateTime cancelledAt = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
                order.setCancelledAt(cancelledAt);
                order.setStatus(OrderStatus.CANCELLED);
                orderRepository.save(order);
            }
        } catch (Exception e) {
            throw new OrderException("Lỗi khi hủy đơn hàng", e);
        }
    }

    @Override
    public Order getOrderByOrderCode(String orderCode) {
        try {
            Order order = orderRepository.getOrderByOrderCode(orderCode);
            return order;
        } catch (Exception e) {
            throw new OrderException("Đơn hàng không tồn tại", e);
        }
    }
}
