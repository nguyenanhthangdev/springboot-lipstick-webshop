package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.exception.CategoryException;
import com.example.webshopcosmetics.model.Category;
import com.example.webshopcosmetics.model.Manufacturer;
import com.example.webshopcosmetics.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/category")
    public String getAllCategory(Model model, @Param("keyword") String keyword, @RequestParam(name="size", defaultValue = "10") String sizeString,
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
        Page<Category> categories = categoryService.getAllCategory(pageNo, keyword, size, status);
        model.addAttribute("active_category", "ACTIVE");
        model.addAttribute("categories", categories);
        model.addAttribute("totalPage", categories.getTotalPages());
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("keyword", keyword);
        model.addAttribute("size", size);
        model.addAttribute("status", status);
        return "admin/category/all-category";
    }

    @GetMapping("/admin/category/add-category")
    public String addCategory(Model model) {
        model.addAttribute("active_category", "ACTIVE");
        model.addAttribute("category", new Category());
        model.addAttribute("parent_categories", categoryService.getAllCategoryHierarchy());
        return "admin/category/add-category";
    }

    @PostMapping("/admin/category/add-category")
    public String saveCategory(@RequestParam("featured") boolean featured, @RequestParam("status") boolean status,
                               @RequestParam("name") String name, @RequestParam(value = "parent_id", defaultValue = "0") Category parent_id,
                               @RequestParam("image") String image, RedirectAttributes redirectAttributes) {
        try {
            Category category = new Category();
            category.setImage(image);
            category.setName(name);
            category.setStatus(status);
            category.setFeatured(featured);
            category.setParent_id(parent_id);
            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            category.setCreatedAt(currentDateTime);
            category.setUpdatedAt(currentDateTime);
            categoryService.saveCategory(category);
            String successMessage = "Thêm danh mục sản phẩm thành công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/category";
        } catch (CategoryException e) {
            String errorMessage = "Thêm danh mục sản phẩm không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/category";
        }
    }

    @GetMapping("/admin/category/edit-category")
    public String editCategory(Model model, @Param("id") Long id) {
        try {
            model.addAttribute("active_category", "ACTIVE");
            model.addAttribute("category", categoryService.getOneCategory(id));
            model.addAttribute("parent_categories", categoryService.getAllCategoryForEdit(id));
            return "admin/category/edit-category";
        } catch (CategoryException e) {
            String errorMessage = "Danh mục sản phẩm không tồn tại";
            model.addAttribute("errorMessage", errorMessage);
            return "redirect:/admin/category";
        }
    }

    @PostMapping("/admin/category/edit-category")
    public String updateCategory(@RequestParam(value = "category_id") long id, @RequestParam("image") String image,
                                 @RequestParam("featured") boolean featured, @RequestParam("status") boolean status,
                                 @RequestParam("name") String name, @RequestParam(value = "parent_id", defaultValue = "0") Category parent_id,
                                 RedirectAttributes redirectAttributes) {
        try {
            Category category = categoryService.getOneCategory(id);
            category.setImage(image);
            category.setName(name);
            category.setStatus(status);
            category.setFeatured(featured);
            category.setParent_id(parent_id);
            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            category.setUpdatedAt(currentDateTime);
            categoryService.updateCategory(category);
            String successMessage = "Thay đổi thông tin danh mục sản phẩm thành công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/category";
        } catch (CategoryException e) {
            String errorMessage = "Thay đổi thông tin danh mục sản phẩm không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/category";
        }
    }

    @GetMapping("/admin/category/delete-category")
    public String deleteCategory(@Param("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            Category category = categoryService.getOneCategory(id);
            categoryService.deleteCategory(id);
            String successMessage = "Xóa danh mục sản phẩm thành công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/category";
        } catch (CategoryException e) {
            String errorMessage = "Xóa danh mục sản phẩm không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/category";
        }
    }
    //-----------------------------------------HOME-------------------------------------------//
}
