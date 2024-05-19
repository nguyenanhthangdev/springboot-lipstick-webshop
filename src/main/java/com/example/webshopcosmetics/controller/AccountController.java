package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.dto.CustomerDTO;
import com.example.webshopcosmetics.dto.UserDTO;
import com.example.webshopcosmetics.model.Customer;
import com.example.webshopcosmetics.service.category.CategoryService;
import com.example.webshopcosmetics.service.customer.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/cosmetic/my-account")
    public String myAccount(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Customer customerAccount = customerService.myAccount(session);
        if (customerAccount != null) {
            model.addAttribute("account", customerAccount);
            model.addAttribute("account_active", "ACTIVE");
            model.addAttribute("categories", categoryService.getAllCategory());
            return "client/order/my-account";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi khi truy cập");
            return "redirect:/cosmetic";
        }
    }

    @PostMapping("/cosmetic/check-password-old")
    @ResponseBody
    public boolean checkIfTheOldPasswordIsCorrect(@RequestParam("passwordOld") String passwordOld, HttpSession session) {
        Customer customerAccount = customerService.myAccount(session);
        if (!customerService.checkIfTheOldPasswordIsCorrect(customerAccount.getAccount(), passwordOld)) {
            return false;
        } else {
            return true;
        }
    }

    @PostMapping("/cosmetic/change-password")
    @ResponseBody
    public ResponseEntity<?> changePassword(@RequestParam("passwordNew") String passwordNew, HttpSession session) {
        try {
            Customer customer = customerService.myAccount(session);
            if (customer == null) {
                return ResponseEntity.ok(false);
            } else {
                System.out.println("account: " + customer.getAccount());
                customer.setPassword(passwordNew);
                customerService.updateCustomer(customer);
                return ResponseEntity.ok(true);
            }
        } catch (Exception e) {
            String errorMessage = "Lỗi khi thay đổi mật khẩu";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @PostMapping("/cosmetic/update-info-account")
    @ResponseBody
    public ResponseEntity<?> updateInfoAccount(@RequestParam("fullName") String fullName, @RequestParam("email") String email, HttpSession session) {
        try {
            Customer customer = customerService.myAccount(session);
            if (customer == null) {
                return ResponseEntity.ok(false);
            } else {
                customer.setFullname(fullName);
                customer.setEmail(email);
                Customer customerSaved = customerService.updateCustomer(customer);
                if (customerSaved != null) {
                    CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
                    CustomerDTO updatedCustomerDTO = new CustomerDTO(customerDTO.id(), fullName, customerDTO.account());
                    session.setAttribute("s_customer", updatedCustomerDTO);
                    CustomerDTO customerDTO1 = (CustomerDTO) session.getAttribute("s_customer");
                }
                return ResponseEntity.ok(true);
            }
        } catch (Exception e) {
            String errorMessage = "Lỗi khi thay thông tin tài khoản";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}
