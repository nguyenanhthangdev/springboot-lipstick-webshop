package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.exception.BannerException;
import com.example.webshopcosmetics.exception.CategoryException;
import com.example.webshopcosmetics.model.Banner;
import com.example.webshopcosmetics.model.BannerOption;
import com.example.webshopcosmetics.model.Category;
import com.example.webshopcosmetics.model.Manufacturer;
import com.example.webshopcosmetics.service.banner.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("/admin/banner")
    public String getAllBanner(Model model) {
        model.addAttribute("active_banner", "ACTIVE");
        model.addAttribute("banners", bannerService.getAllBanner());
        return "admin/banner/all-banner";
    }

    @GetMapping("/admin/banner/add-banner")
    public String addBanner(Model model) {
        model.addAttribute("active_banner", "ACTIVE");
        model.addAttribute("banner", new Banner());
        return "admin/banner/add-banner";
    }

    @PostMapping("/admin/banner/add-banner")
    public String saveBanner(Model model, @RequestParam(value = "image") String image, @RequestParam(value="bannerType")BannerOption bannerOption,
                             @RequestParam("status") boolean status, @RequestParam("link") String link,
                             RedirectAttributes redirectAttributes) {
        try {
            Banner banner = new Banner();
            banner.setImage(image);
            banner.setOption(bannerOption);
            banner.setLink(link);
            banner.setStatus(status);
            bannerService.saveBanner(banner);
            String successMessage = "Thêm Ảnh Banner Thành Công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/banner";
        } catch (BannerException e) {
            String errorMessage = "Thêm Ảnh Banner Không Thành Công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/banner";
        }
    }

    @GetMapping("/admin/banner/edit-banner")
    public String editBanner(Model model, @Param("id") Long id) {
        model.addAttribute("active_banner", "ACTIVE");
        model.addAttribute("banner", bannerService.getOneBanner(id));
        return "admin/banner/edit-banner";
    }

    @PostMapping("/admin/banner/edit-banner")
    public String updateBanner(Model model, @RequestParam(value = "banner_id") Long banner_id,@RequestParam(value="bannerType")BannerOption bannerOption,
                               @RequestParam(value = "image") String image, @RequestParam("status") boolean status,
                               @RequestParam("link") String link, RedirectAttributes redirectAttributes) {
        try {
            Banner banner = bannerService.getOneBanner(banner_id);
            banner.setImage(image);
            banner.setOption(bannerOption);
            banner.setLink(link);
            banner.setStatus(status);
            bannerService.saveBanner(banner);
            String successMessage = "Thay Đổi Thông Tin Ảnh Banner Thành Công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/banner";
        } catch (BannerException e) {
            String errorMessage = "Thay Đổi Thông Tin Ảnh Banner Không Thành Công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/banner";
        }
    }

    @GetMapping("/admin/banner/delete-banner")
    public String deleteBanner(@Param("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            Banner banner = bannerService.getOneBanner(id);
            bannerService.deleteBanner(id);
            String successMessage = "Xóa Ảnh Banner Thành Công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/banner";
        } catch (CategoryException e) {
            String errorMessage = "Xóa Ảnh Banner Không Thành Công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/banner";
        }
    }
}
