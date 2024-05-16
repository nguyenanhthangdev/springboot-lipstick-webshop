package com.example.webshopcosmetics.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {org.springframework.web.servlet.NoHandlerFoundException.class})
    public String handleNotFoundError(Exception ex, HttpServletRequest request) {
        return "redirect:/errr/404";
//        String path = request.getRequestURI();
//        if (path.startsWith("/admin/")) {
//            return "redirect:/admin/error/404";
//        } else if (path.startsWith("/cosmetic/")) {
//            return "redirect:/cosmetic/error/404";
//        } else {
//            return "redirect:/error/404";
//        }
    }
}
