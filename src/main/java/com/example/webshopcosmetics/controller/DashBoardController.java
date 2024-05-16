package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.dto.UserDTO;
import com.example.webshopcosmetics.model.Order;
import com.example.webshopcosmetics.model.User;
import com.example.webshopcosmetics.model.WebsiteSetting;
import com.example.webshopcosmetics.service.order.OrderService;
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
    private OrderService orderService;

    @Autowired
    private WebsiteSettingService websiteSettingService;

    @GetMapping("/admin/config-session-user")
    public String configSessionUser(Model model, HttpSession session, Authentication authentication, HttpServletRequest request) {
        if (authentication != null && authentication.isAuthenticated()) {
            // Lấy thông tin người dùng từ đối tượng Authentication
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            User user = userService.findByUsername(userDetails.getUsername());

            // Đưa thông tin người dùng vào session
            session.setAttribute("s_user", new UserDTO(user.getId(), user.getFullname(), user.getUsername(), user.getImage()));
            List<WebsiteSetting> webshopSettings = websiteSettingService.getAllWebsiteSetting();
            session.setAttribute("webshopSettings", webshopSettings);
            return "redirect:/admin/dashboard";
        } else {
            return "admin/sign-in/sign-in";
        }
    }

    @GetMapping("/admin/dashboard")
    public String dashBoard(Model model) {
        List<Order> listOrderShipped = orderService.pickUpAllOrdersThatAreBeingDelivered();
        List<Order> listOrderProcessing = orderService.getAllOrdersInProcess();
        List<Order> listOrderCancelled = orderService.getAllTheOrdersWasAbort();
        List<Order> listOrderDelivered = orderService.getAllOrdersThatHaveBeenSuccessfullyDelivered();
        int numberOrderShipped = 0; int numberOrderProcessing = 0; int numberOrderCancelled = 0; int numberOrderDelivered = 0;
        if (listOrderShipped != null) {
            numberOrderShipped = listOrderShipped.size();
        }
        if (listOrderProcessing != null) {
            numberOrderProcessing = listOrderProcessing.size();
        }
        if (listOrderDelivered != null) {
            numberOrderDelivered = listOrderDelivered.size();
        }
        if (listOrderCancelled != null) {
            numberOrderCancelled = listOrderCancelled.size();
        }
        model.addAttribute("numberOrderProcessing", numberOrderProcessing);
        model.addAttribute("numberOrderShipped", numberOrderShipped);
        model.addAttribute("numberOrderDelivered", numberOrderDelivered);
        model.addAttribute("numberOrderCancelled", numberOrderCancelled);
        model.addAttribute("dashboard", "ACTIVE");
        return "admin/dashboard/dashboard";
    }
}
