package com.example.webshopcosmetics.service.order;

import com.example.webshopcosmetics.model.CancellationReason;
import com.example.webshopcosmetics.model.Order;
import com.example.webshopcosmetics.model.OrderStatus;
import com.example.webshopcosmetics.model.Product;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    public List<Order> getALlOrders();

    public List<Order> getOrderByCustomerId(HttpSession session);

    public Page<Order> getAllOrder(int pageNo, String orderCode, int size, String status);

    public List<Order> getAllTheOrdersWasAbort();

    public List<Order> getAllOrdersInProcess();

    public List<Order> pickUpAllOrdersThatAreBeingDelivered();

    public List<Order> getAllOrdersThatHaveBeenSuccessfullyDelivered();

    public void cancelOrder(CancellationReason cancellationReason, Long orderID, HttpSession session);

    public Order getOrderByOrderCode(String orderCode);

    public void deliveryConfirmation(Long orderID);

    public void deliveredSuccessfully(Long orderID);
}
