package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.dto.CustomerDTO;
import com.example.webshopcosmetics.service.customer.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CheckLoginController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/cosmetic/check-login-checkout")
    @ResponseBody
    public boolean checkLoginCheckout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
        if (customerDTO != null) {
            return true;
        }
        return false;
    }
}
