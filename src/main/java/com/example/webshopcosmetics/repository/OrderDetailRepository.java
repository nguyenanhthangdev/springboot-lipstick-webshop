package com.example.webshopcosmetics.repository;

import com.example.webshopcosmetics.model.OrderDetail;
import com.example.webshopcosmetics.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query("SELECT o FROM OrderDetail o WHERE o.order.id = :order_id")
    List<OrderDetail> getAllOrderDetailsByOrderId(Long order_id);
}
