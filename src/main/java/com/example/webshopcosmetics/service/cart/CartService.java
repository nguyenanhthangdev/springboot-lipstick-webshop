package com.example.webshopcosmetics.service.cart;

import com.example.webshopcosmetics.model.CartDataItem;
import com.example.webshopcosmetics.model.Customer;
import com.example.webshopcosmetics.model.ProductOptions;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    public void addItemToCartInDataBase(Long productId, Long productOptionId, int quantity, Long customerId);
    public void updateQuantityItemToCartInDataBase(Long customerId, Long productOptionId, int quantity);
    public void deleteItemToCartInDataBase(Long customerId, Long productOptionId);
    public void deleteAllItemToCartInDataBase(HttpSession session);
    public ProductOptions findProductOptionsById(Long productOptionsId);
    public void getAllProductOptionsOfCustomerInCartDB(Long customer_id, HttpServletRequest request);
    public void addAllItemToCartInDataBase(Customer customer, HttpServletRequest request);
}
