package com.example.webshopcosmetics.repository;

import com.example.webshopcosmetics.model.Manufacturer;
import com.example.webshopcosmetics.model.Order;
import com.example.webshopcosmetics.model.OrderDetail;
import com.example.webshopcosmetics.model.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.customer.customer_id = :customer_id")
    Order getOrderByCustomerId(Long customer_id);

    @Query("SELECT o FROM Order o WHERE o.customer.customer_id = :customer_id ORDER BY o.orderDate DESC")
    List<Order> getALlOrderByCustomerId(Long customer_id);

    @Query("SELECT o FROM Order o WHERE o.orderCode LIKE %:orderCode%")
    Page<Order> findByOrderCodeContainingIgnoreCase(String orderCode, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE (:status IS NULL OR o.status = :status) AND o.orderCode LIKE %:orderCode%")
    Page<Order> findByOrderCodeAndStatus(String orderCode, OrderStatus status, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE  o.status = :orderStatus")
    List<Order> getAllTheOrdersWasAbort(OrderStatus orderStatus);

    @Query("SELECT o FROM Order o WHERE  o.status = :orderStatus")
    public List<Order> getAllOrdersInProcess(OrderStatus orderStatus);

    @Query("SELECT o FROM Order o WHERE  o.status = :orderStatus")
    public List<Order> pickUpAllOrdersThatAreBeingDelivered(OrderStatus orderStatus);

    @Query("SELECT o FROM Order o WHERE  o.status = :orderStatus")
    public List<Order> getAllOrdersThatHaveBeenSuccessfullyDelivered(OrderStatus orderStatus);

    @Query("SELECT o FROM Order o ORDER BY o.orderDate DESC")
    List<Order> getALlOrders();

    @Query("SELECT o FROM Order o WHERE o.orderCode = :orderCode")
    Order getOrderByOrderCode(String orderCode);
}
