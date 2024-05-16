package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignInController {

    @GetMapping("/admin/sign-in")
    public String signIn() {
        return "admin/sign-in/sign-in";
    }
}
