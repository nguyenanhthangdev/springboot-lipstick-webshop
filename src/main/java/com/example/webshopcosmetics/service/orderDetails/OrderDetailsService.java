package com.example.webshopcosmetics.service.orderDetails;

import com.example.webshopcosmetics.model.Order;
import com.example.webshopcosmetics.model.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailsService {
    public List<Order> getAllOrderDetailByOrderId(List<Order> orders);

    public Order getOrderDetailByOrderId(Order order);
}

