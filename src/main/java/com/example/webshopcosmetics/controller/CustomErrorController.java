//package com.example.webshopcosmetics.controller;
//
//import com.example.webshopcosmetics.service.category.CategoryService;
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class CustomErrorController implements ErrorController {
//    @Autowired
//    private CategoryService categoryService;
//
//    @RequestMapping("/error")
//    public String handleError(HttpServletRequest request, Model model) {
//        String deleteType = (String) request.getAttribute("deleteType");
//        String errorMessage = (String) request.getAttribute("errorMessage");
//        System.out.println("deleteType: " + deleteType);
//        System.out.println("deleteTy  : " + errorMessage);
//        if ("product".equals(deleteType)) {
//            // Xử lý ngoại lệ do người dùng xóa sản phẩm
//            return "error-product-deletion";
//        } else if ("category".equals(deleteType)) {
//            // Xử lý ngoại lệ do người dùng xóa danh mục sản phẩm
////            if (!model.containsAttribute("categories")) {
////                model.addAttribute("active_category", "ACTIVE");
////                model.addAttribute("categories", categoryService.getAllCategory());
////            }
////            model.addAttribute("errorAlert", "Xóa danh mục sản phẩm không thành công");
//            return "admin/category/all-category";
//        } else {
//            // Xử lý các loại ngoại lệ khác nếu cần
//            return "admin/error-page/error-page";
//        }
//    }
//
////    @Override
////    public String getErrorPath() {
////        return "/error";
////    }
//}