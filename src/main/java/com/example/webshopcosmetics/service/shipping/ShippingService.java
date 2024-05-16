package com.example.webshopcosmetics.service.shipping;

import com.example.webshopcosmetics.model.Customer;
import com.example.webshopcosmetics.model.Shipping;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShippingService {
    public boolean hasShippingAddress(HttpSession session);
    public Shipping saveShipping(Shipping shipping);
    public Shipping findShippingByCustomerIdAndStatusTrue(HttpSession session);
    public List<Shipping> findAllShippingByCustomerId(HttpSession session);
    public List<Shipping> getAllAddressOfCustomer(HttpSession session);
    public void deactivateAllByCustomerId(HttpSession session);
    public Shipping getOneShippingById(Long id);
    public void deleteShipingById(Long id);
    public Shipping checkCustomerHasShipping(HttpSession session);
    public void updateStatusTrueByShippingId(Long shippingId);
}
