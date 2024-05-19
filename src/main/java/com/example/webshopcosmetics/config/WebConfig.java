package com.example.webshopcosmetics.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CookieInterceptor cookieInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cookieInterceptor).addPathPatterns("/cosmetic/**")
                .excludePathPatterns("/cosmetic", "/cosmetic/product-details", "/cosmetic/cart", "/cosmetic/check-logged-checkout", "/cosmetic/check-the-user-has-address-information",
                        "/cosmetic/register", "/cosmetic/login", "/cosmetic/logout", "/cosmetic/search", "/cosmetic/category", "/cosmetic/manufacturer", "/cosmetic/get-all-address-of-customer",
                        "/cosmetic/check-account-exists", "/cosmetic/check-login-checkout", "/cosmetic/check-login", "/cosmetic/save-review-product","/cosmetic/get-all-city",
                        "/cosmetic/add-to-cart", "/cosmetic/cart-mini", "/cosmetic/update-quantity-cart", "/cosmetic/load-number-product-in-cart", "/cosmetic/save-shipping-of-customer",
                        "/cosmetic/save-shipping-of-customer2", "/cosmetic/delete-shipping-by-id-of-customer", "/cosmetic/save-order", "/cosmetic/check-customer-has-shipping",
                        "/cosmetic/check-the-number-of-products-left-in-the-shop", "/cosmetic/products-payment", "/cosmetic/my-order", "/cosmetic/about", "/cosmetic/contact",
                        "/cosmetic/contact/save-contact", "/cosmetic/posts-details", "/cosmetic/all-post", "/cosmetic/get-price-product-option", "/cosmetic/change-this-address-to-default-in-DB",
                        "/cosmetic/save-reason-for-cancellation", "/cosmetic/check-password-old", "/cosmetic/change-password", "/cosmetic/update-info-account");
    }
}
