package com.example.webshopcosmetics.service.orderDetails;

import com.example.webshopcosmetics.model.Order;
import com.example.webshopcosmetics.model.OrderDetail;
import com.example.webshopcosmetics.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
    @Autowired private OrderDetailRepository orderDetailRepository;

    @Override
    public List<Order> getAllOrderDetailByOrderId(List<Order> orders) {
        for (Order order : orders) {
            // Truy vấn tất cả các chi tiết đơn hàng cho mỗi đơn hàng và gán vào danh sách chi tiết đơn hàng tương ứng
            List<OrderDetail> orderDetails = orderDetailRepository.getAllOrderDetailsByOrderId(order.getId());
            // Gán danh sách chi tiết đơn hàng vào đơn hàng hiện tại
            order.setOrderDetails(orderDetails);
        }

        return orders;
    }

    @Override
    public Order getOrderDetailByOrderId(Order order) {
        List<OrderDetail> orderDetails = orderDetailRepository.getAllOrderDetailsByOrderId(order.getId());
        order.setOrderDetails(orderDetails);
        return order;
    }
}
