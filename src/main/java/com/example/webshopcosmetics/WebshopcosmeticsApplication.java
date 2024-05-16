package com.example.webshopcosmetics;

import com.example.webshopcosmetics.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
public class WebshopcosmeticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebshopcosmeticsApplication.class, args);
    }
}