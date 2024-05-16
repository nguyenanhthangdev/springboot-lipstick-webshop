package com.example.webshopcosmetics.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignOutController {

    @GetMapping("/admin/sign-out")
    public String signOut(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        session.removeAttribute("webshopSettings");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Thực hiện một số xử lý nếu cần thiết trước khi đăng xuất
        // Xóa thông tin đăng nhập
        SecurityContextHolder.getContext().setAuthentication(null);

        // Xóa cookies "Remember Me" khi đăng xuất
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("remember-me".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        // Xóa context bảo mật
        new SecurityContextLogoutHandler().logout(request, null, null);

        // Có thể thực hiện các hành động khác khi đăng xuất, ví dụ chuyển hướng đến trang đăng nhập
        return "redirect:/admin/sign-in?logout=true";
    }
}
