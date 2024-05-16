package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.exception.CustomerException;
import com.example.webshopcosmetics.exception.ManufacturerException;
import com.example.webshopcosmetics.model.Customer;
import com.example.webshopcosmetics.model.Manufacturer;
import com.example.webshopcosmetics.service.customer.CustomerService;
import com.example.webshopcosmetics.service.manufacturer.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/admin/customer")
    public String getAllCustomer(Model model, @Param("keyword") String keyword, @RequestParam(name="size", defaultValue = "10") String sizeString,
                                     @RequestParam(name="pageNo", defaultValue = "1") String pageNoString, @RequestParam(value = "status", defaultValue = "2") String statusString) {
        int pageNo = 1;
        try {
            pageNo = Integer.parseInt(pageNoString);
        } catch (NumberFormatException e) {
            pageNo = 1;
        }
        int status = 2;
        try {
            status = Integer.parseInt(statusString);
        } catch (NumberFormatException e) {
            status = 2;
        }
        int size = 10;
        try {
            size = Integer.parseInt(sizeString);
        } catch (NumberFormatException e) {
            size = 10;
        }

        Page<Customer> customers = customerService.getAllCustomer(pageNo,keyword, size, status);
        model.addAttribute("active_customer", "ACTIVE");
        model.addAttribute("customers", customers);
        model.addAttribute("totalPage", customers.getTotalPages());
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("keyword", keyword);
        model.addAttribute("size", size);
        model.addAttribute("status", status);
        return "admin/customer/all-customer";
    }

    @GetMapping("/admin/customer/edit-customer")
    public String editCustomer(Model model, @Param("id") Long id) {
        try {
            model.addAttribute("active_customer", "ACTIVE");
            model.addAttribute("customer", customerService.getOneCustomer(id));
            return "admin/customer/edit-customer";
        } catch (CustomerException e) {
            String errorMessage = "Khách hàng không tồn tại";
            model.addAttribute("errorMessage", errorMessage);
            return "admin/customer";
        }
    }

    @PostMapping("/admin/customer/edit-customer")
    public String updateCustomer(Model model, @Param("customer_id") Long customer_id, @RequestParam("status") boolean status, RedirectAttributes redirectAttributes) {
        try {
            Customer customer = customerService.getOneCustomer(customer_id);
            customer.setStatus(status);
            customerService.updateCustomer(customer);
            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            String successMessage = "Thay đổi trạng thái tài khoản khách hàng thành công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/customer";
        } catch (CustomerException e) {
            String errorMessage = "Thay đổi trạng thái tài khoản khách hàng không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/customer";
        }
    }

    @GetMapping("/admin/customer/delete-customer")
    public String deleteCustomer(@Param("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            Customer customer = customerService.getOneCustomer(id);
            customerService.deleteCustomer(id);
            String successMessage = "Xóa tài khoản khách hàng phẩm thành công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/customer";
        } catch (CustomerException e) {
            String errorMessage = "Xóa tài khoản khách hàng không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/customer";
        }
    }
}
