package com.example.webshopcosmetics.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class CookieFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        // Kiểm tra cookie
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies != null) {
            boolean cookieExists = false;
            for (Cookie cookie : cookies) {
                if ("customerData".equals(cookie.getName())) {
                    cookieExists = true;
                    break;
                }
            }
            if (!cookieExists && session != null) {
                // Xóa session nếu không có cookie
                session.removeAttribute("s_customer");
            }
        } else {
            // Xóa session nếu không có cookie
            if (session != null) {
                session.removeAttribute("s_customer");
            }
        }

        // Chuyển tiếp yêu cầu tới các Filter hoặc Servlet tiếp theo trong chuỗi
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Hủy filter
    }
}
