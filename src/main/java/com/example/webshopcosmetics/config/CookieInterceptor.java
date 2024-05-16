package com.example.webshopcosmetics.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class CookieInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Kiểm tra cookie
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            redirectToHome(request, response);
            return false;
        }

        // Kiểm tra cookie có tên là "HavanaShop"
        boolean validCookie = false;
        for (Cookie cookie : cookies) {
            if ("customerData".equals(cookie.getName())) {
                validCookie = true;
                break;
            }
        }

        if (!validCookie) {
            redirectToHome(request, response);
            return false;
        }

        return true;
    }

    private void redirectToHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Chuyển hướng về trang chủ
        response.sendRedirect(request.getContextPath() + "/cosmetic");
    }

}