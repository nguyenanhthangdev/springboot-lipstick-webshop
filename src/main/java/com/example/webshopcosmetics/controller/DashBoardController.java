package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.dto.UserDTO;
import com.example.webshopcosmetics.model.User;
import com.example.webshopcosmetics.model.WebsiteSetting;
import com.example.webshopcosmetics.service.user.UserService;
import com.example.webshopcosmetics.service.websiteSetting.WebsiteSettingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DashBoardController {
    @Autowired
    private UserService userService;

    @Autowired
    private WebsiteSettingService websiteSettingService;

    @GetMapping("/admin/dashboard")
    public String dashBoard(Model model, HttpSession session, Authentication authentication, HttpServletRequest request) {
        if (authentication != null && authentication.isAuthenticated()) {
            // Lấy thông tin người dùng từ đối tượng Authentication
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            User user = userService.findByUsername(userDetails.getUsername());

            // Đưa thông tin người dùng vào session
            session.setAttribute("s_user", new UserDTO(user.getId(), user.getFullname(), user.getUsername(), user.getImage()));
            model.addAttribute("dashboard", "ACTIVE");
            List<WebsiteSetting> webshopSettings = websiteSettingService.getAllWebsiteSetting();
            session.setAttribute("webshopSettings", webshopSettings);
            return "admin/dashboard/dashboard";
        } else {
            return "admin/sign-in/sign-in";
        }
    }
}
