package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.exception.AboutException;
import com.example.webshopcosmetics.exception.ManufacturerException;
import com.example.webshopcosmetics.model.About;
import com.example.webshopcosmetics.service.about.AboutService;
import com.example.webshopcosmetics.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Controller
public class AboutController {
    @Autowired private AboutService aboutService;
    @Autowired private CategoryService categoryService;

    @GetMapping("/admin/about")
    public String about(Model model) {
        model.addAttribute("abouts", aboutService.getAllABout());
        model.addAttribute("active_about", "ACTIVE");
        return "admin/about/about";
    }

    @PostMapping("/admin/about/edit-about")
    public String saveAbout(Model model, @RequestParam(value = "name_shop") String name_shop, @RequestParam("introduce") String introduce,
                            @RequestParam("information") String information, @RequestParam("address") String address, @RequestParam("about_id") Long about_id,
                            @RequestParam(value = "phone") String phone, @RequestParam("email") String email, RedirectAttributes redirectAttributes,
                            @RequestParam("fanpage") String fanpage, @RequestParam("founding") String founding, @RequestParam("google_map") String google_map) {
        try {
            About about = aboutService.getOneAboutById(about_id);
            about.setNameShop(name_shop);
            about.setIntroduce(introduce);
            about.setInformation(information);
            about.setAddress(address);
            about.setGoogleMap(google_map);
            about.setEmail(email);
            about.setPhone(phone);
            about.setFanpage(fanpage);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(founding);
            about.setFounding(date);
            aboutService.saveAbout(about);
            String errorMessage = "Thay đổi thông tin thành công";
            redirectAttributes.addFlashAttribute("successMessage", errorMessage);
            return "redirect:/admin/about";
         } catch (AboutException | ParseException e) {
            String errorMessage = "Thay đổi thông tin không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/about";
        }
    }

    @GetMapping("/cosmetic/about")
    public String aboutHome(Model model) {
        model.addAttribute("abouts", aboutService.getAllABout());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "client/about/about";
    }

}
