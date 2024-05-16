package com.example.webshopcosmetics.repository;

import com.example.webshopcosmetics.model.Shipping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.webshopcosmetics.model.Customer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {
    boolean existsByCustomer(Customer customer);

    @Query("SELECT s FROM Shipping s WHERE s.customer.customer_id = :customerId AND s.status = true")
    Shipping findShippingsByCustomerIdAndStatusTrue(Long customerId);

    @Query("SELECT s FROM Shipping s WHERE s.customer.customer_id = :customerId")
    List<Shipping> findAllShippingsByCustomerId(Long customerId);

    @Query("SELECT s FROM Shipping s WHERE s.customer.customer_id = :accountId")
    List<Shipping> findAllShippingOfCustomer(Long accountId);

    @Transactional
    @Modifying
    @Query("UPDATE Shipping s SET s.status = false WHERE s.customer.customer_id = :customerId")
    void deactivateAllByCustomerId(Long customerId);

    @Query("SELECT s FROM Shipping s WHERE s.customer.customer_id = :customerId AND s.status = true")
    Shipping findFirstByCustomerIdAndStatusTrue(Long customerId);

    Optional<Shipping> findById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Shipping s SET s.status = true WHERE s.id = :shippingId")
    void updateStatusTrueByShippingId(Long shippingId);



}