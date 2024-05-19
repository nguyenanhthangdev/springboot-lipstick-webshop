package com.example.webshopcosmetics.service.customer;

import com.example.webshopcosmetics.model.Banner;
import com.example.webshopcosmetics.model.Customer;

import com.example.webshopcosmetics.model.Manufacturer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    public Customer findCustomerByAccount(String account);
    public Customer registerCustomer(Customer customer);
    public Customer authenticateCustomer(String account, String password);
    public Customer myAccount(HttpSession session);
    public boolean checkIfTheOldPasswordIsCorrect(String account, String password);

    //=======================================ADMIN===============================================
    public Page<Customer> getAllCustomer(int pageNo, String keyword, int size, int status);
    public Customer getOneCustomer(Long id);
    public Customer updateCustomer(Customer customer);
    public void deleteCustomer(Long id);
}
