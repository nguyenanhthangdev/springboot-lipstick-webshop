package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.exception.CategoryException;
import com.example.webshopcosmetics.exception.ManufacturerException;
import com.example.webshopcosmetics.model.Category;
import com.example.webshopcosmetics.model.Manufacturer;
import com.example.webshopcosmetics.model.Product;
import com.example.webshopcosmetics.model.User;
import com.example.webshopcosmetics.service.manufacturer.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Controller
//@RequestMapping("/manufacturer")
public class ManufacturerController {
    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping("/admin/manufacturer")
    public String getAllManufacturer(Model model, @Param("keyword") String keyword, @RequestParam(name="size", defaultValue = "10") String sizeString,
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

        Page<Manufacturer> manufacturers = manufacturerService.getAllManufacturer(pageNo,keyword, size, status);
        model.addAttribute("active_manufacturer", "ACTIVE");
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("totalPage", manufacturers.getTotalPages());
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("keyword", keyword);
        model.addAttribute("size", size);
        model.addAttribute("status", status);
        return "admin/manufacturer/all-manufacturer";
    }

    @GetMapping("/admin/manufacturer/add-manufacturer")
    public String addManufacturer(Model model) {
        model.addAttribute("active_manufacturer", "ACTIVE");
        model.addAttribute("manufacturer", new Manufacturer());
        return "admin/manufacturer/add-manufacturer";
    }

    @PostMapping("/admin/manufacturer/add-manufacturer")
    public String saveManufacturer(@RequestParam(value = "image") String image, @RequestParam("featured") boolean featured,
                                   @RequestParam("status") boolean status, @RequestParam("name") String name,
                                   RedirectAttributes redirectAttributes) {
        try {
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setImage(image);
            manufacturer.setName(name);
            manufacturer.setFeatured(featured);
            manufacturer.setStatus(status);
            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            manufacturer.setCreatedAt(currentDateTime);
            manufacturer.setUpdatedAt(currentDateTime);
            manufacturerService.saveManufacturer(manufacturer);
            String successMessage = "Thêm thương hiệu sản phẩm thành công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/manufacturer";
        } catch (ManufacturerException e) {
            String errorMessage = "Thêm thương hiệu sản phẩm không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/manufacturer";
        }
    }

    @GetMapping("/admin/manufacturer/edit-manufacturer")
    public String editManufacturer(Model model, @Param("id") Long id) {
        try {
            model.addAttribute("active_manufacturer", "ACTIVE");
            model.addAttribute("manufacturer", manufacturerService.getOneManufacturer(id));
            return "admin/manufacturer/edit-manufacturer";
        } catch (ManufacturerException e) {
            String errorMessage = "Thương hiệu sản phẩm không tồn tại";
            model.addAttribute("errorMessage", errorMessage);
            return "admin/manufacturer";
        }
    }

    @PostMapping("/admin/manufacturer/edit-manufacturer")
    public String updateManufacturer(@RequestParam(value = "manufacturer_id") long id, @RequestParam(value = "image") String image,
                                     @RequestParam("featured") boolean featured, @RequestParam("status") boolean status,
                                     @RequestParam("name") String name, RedirectAttributes redirectAttributes) {
        try {
            Manufacturer manufacturer = manufacturerService.getOneManufacturer(id);
            manufacturer.setImage(image);
            manufacturer.setName(name);
            manufacturer.setFeatured(featured);
            manufacturer.setStatus(status);
            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            manufacturer.setUpdatedAt(currentDateTime);
            manufacturerService.updateManufacturer(manufacturer);
            String successMessage = "Thay đổi thông tin thương hiệu sản phẩm thành công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/manufacturer";
        } catch (ManufacturerException e) {
            String errorMessage = "Thay đổi thông tin thương hiệu sản phẩm không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/manufacturer";
        }
    }

    @GetMapping("/admin/manufacturer/delete-manufacturer")
    public String deleteManufacturer(@Param("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            Manufacturer manufacturer = manufacturerService.getOneManufacturer(id);
            manufacturerService.deleteManufacturer(id);
            String successMessage = "Xóa thương hiệu sản phẩm thành công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/manufacturer";
        } catch (ManufacturerException e) {
            String errorMessage = "Xóa thương hiệu sản phẩm không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/manufacturer";
        }
    }
}
