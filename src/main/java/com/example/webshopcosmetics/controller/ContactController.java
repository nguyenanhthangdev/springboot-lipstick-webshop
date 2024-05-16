package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.exception.ContactException;
import com.example.webshopcosmetics.model.Contact;
import com.example.webshopcosmetics.service.about.AboutService;
import com.example.webshopcosmetics.service.category.CategoryService;
import com.example.webshopcosmetics.service.contact.ContactService;
import org.hibernate.validator.internal.util.Contracts;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ContactController {
    @Autowired private AboutService aboutService;
    @Autowired private ContactService contactService;
    @Autowired private CategoryService categoryService;

    @GetMapping("/cosmetic/contact")
    public String contactHome(Model model) {
        model.addAttribute("abouts", aboutService.getAllABout());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "client/contact/contact";
    }

    @GetMapping("/admin/contact")
    public String allContact(Model model) {
        model.addAttribute("active_contact", "ACTIVE");
        model.addAttribute("contacts", contactService.getAllContact());
        return "admin/contact/all-contact";
    }

    @GetMapping("/admin/contact/contact-details")
    public String contactDetails(Model model, @Param("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("active_contact", "ACTIVE");
            model.addAttribute("contact", contactService.getOneContact(id));
            return "/admin/contact/contact-details";
        } catch (ContactException e) {
            String errorMessage = "Xem chi tiết ý kiến của khách hàng thất bại";
            model.addAttribute("errorMessage", errorMessage);
            return "redirect:/cosmetic/contact";
        }
    }

    @PostMapping("/cosmetic/contact/save-contact")
    public String saveContact(Model model, @RequestParam(value = "fullName") String fullName, @RequestParam("title_contact") String title_contact,
                              @RequestParam("detail_contact") String detail_contact, @RequestParam(value = "phone") String phone, @RequestParam("email") String email, RedirectAttributes redirectAttributes) {
        try {
            Contact contact = new Contact();
            contact.setContactTitle(title_contact);
            contact.setContactDetail(detail_contact);
            contact.setFullName(fullName);
            contact.setPhone(phone);
            contact.setEmail(email);
            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            contact.setCreatedAt(currentDateTime);
            contactService.saveContact(contact);
            String successMessage = "Gửi tin nhắn thành công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/cosmetic/contact";
        } catch (ContactException e) {
            String errorMessage = "Gửi tin nhắn không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/cosmetic/contact";
        }
    }
}
