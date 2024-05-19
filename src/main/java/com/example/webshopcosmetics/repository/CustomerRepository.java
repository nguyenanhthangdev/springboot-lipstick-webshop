package com.example.webshopcosmetics.repository;

import com.example.webshopcosmetics.model.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.webshopcosmetics.model.Customer;
import com.example.webshopcosmetics.model.User;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.account = :account")
    Customer findByAccount(String account);

    Page<Customer> findByAccountContaining(String keyword, Pageable pageable);

    Page<Customer> findByStatusAndAccountContaining(Boolean status, String account, Pageable pageable);
}