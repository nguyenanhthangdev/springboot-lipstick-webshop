package com.example.webshopcosmetics.service.checkout;

import com.example.webshopcosmetics.model.ProductOptions;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public interface CheckoutService {
    public void checkTheNumberOfProductsLeftInTheShop(HttpSession session);

    public void productsPayment(HttpSession session);
}
