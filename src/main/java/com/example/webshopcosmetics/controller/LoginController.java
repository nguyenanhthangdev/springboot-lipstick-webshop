package com.example.webshopcosmetics.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.example.webshopcosmetics.dto.CustomerDTO;
import com.example.webshopcosmetics.model.CartDataItem;
import com.example.webshopcosmetics.model.CartItem;
import com.example.webshopcosmetics.model.ProductReview;
import com.example.webshopcosmetics.service.cart.CartService;
import com.example.webshopcosmetics.service.productReview.ProductReviewService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.webshopcosmetics.exception.RegisterException;
import com.example.webshopcosmetics.model.Customer;
import com.example.webshopcosmetics.service.customer.CustomerService;

@Controller
public class LoginController {
    @Autowired private CustomerService customerService;
    @Autowired private CartService cartService;
    @Autowired private ProductReviewService productReviewService;

    @GetMapping("/cosmetic/login")
    public String login() {
        return "client/authentication/login";
    }

    @PostMapping("/cosmetic/register")
    @ResponseBody
    public ResponseEntity<?> saveCustomer(@RequestParam("fullName") String fullName, @RequestParam("account") String account, @RequestParam("email") String email,
                                          @RequestParam("password") String password, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            Customer customer = new Customer();
            customer.setFullname(fullName);
            customer.setAccount(account);
            customer.setEmail(email);
            customer.setPassword(password);
            customer.setStatus(true);
            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            customer.setCreatedAt(currentDateTime);
            customer.setUpdatedAt(currentDateTime);
            Customer customer1 = customerService.registerCustomer(customer);
            cartService.addAllItemToCartInDataBase(customer1, request);
            return ResponseEntity.ok(customer1);
        } catch (RegisterException e) {
            String errorMessage = "Đăng kí tài khoản không thành công. Vui lòng thử lại sau!";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @PostMapping("/cosmetic/check-account-exists")
    @ResponseBody
    public String checkAccountExists(@RequestParam("account") String account) {
        Customer customer = customerService.findCustomerByAccount(account);
        if (customer != null) {
            return "false";
        } else {
            return "true";
        }
    }

    @GetMapping("/cosmetic/forgot-password")
    public String forgotPassword() {
        return "client/authentication/forgot-password";
    }

    @PostMapping("/cosmetic/login")
    @ResponseBody
    public ResponseEntity<?> LoginCustomer(@RequestParam("account") String account, @RequestParam("password") String password,
                                          RedirectAttributes redirectAttributes, HttpServletRequest request) {
        try {
            Customer customer = customerService.authenticateCustomer(account, password);
            if (customer != null) {
                cartService.getAllProductOptionsOfCustomerInCartDB(customer.getCustomer_id(), request);
                return ResponseEntity.ok(customer);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tài khoản hoặc mật khẩu không đúng");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi trong quá trình đăng nhập");
        }
    }

    @PostMapping("/cosmetic/logout")
    @ResponseBody
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Xóa cookie
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("customerData".equals(cookie.getName())) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                        break;
                    }
                }
            }
            // Xóa session
            HttpSession session = request.getSession();
            CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
            if (customerDTO != null) {
                session.removeAttribute("s_customer");
                session.invalidate();
                System.out.println("check_s: " + customerDTO);
            }
            return ResponseEntity.ok("Đã đăng xuất");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi đăng xuất");
        }
    }

    @GetMapping("/cosmetic/check-login")
    @ResponseBody
    public String checkLogin(HttpSession session, @RequestParam("productId") Long productId) {
        CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
        if (customerDTO == null) {
            return "false";
        } else {
            ProductReview productReview = productReviewService.checkCustomerReviewProduct(customerDTO.id(), productId);
            if (productReview != null) { return "true1"; } else { return "true2"; }
        }
    }
}
