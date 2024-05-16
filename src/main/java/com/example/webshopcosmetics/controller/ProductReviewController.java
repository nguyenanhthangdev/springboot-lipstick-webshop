package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.dto.CustomerDTO;
import com.example.webshopcosmetics.exception.ManufacturerException;
import com.example.webshopcosmetics.exception.ProductReviewException;
import com.example.webshopcosmetics.model.ProductReview;
import com.example.webshopcosmetics.repository.CustomerRepository;
import com.example.webshopcosmetics.repository.ProductRepository;
import com.example.webshopcosmetics.repository.ProductReviewRepository;
import com.example.webshopcosmetics.service.productReview.ProductReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Controller
public class ProductReviewController {
    @Autowired private ProductReviewRepository productReviewRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private ProductReviewService productReviewService;

    @PostMapping("/cosmetic/save-review-product")
    @ResponseBody
    public String saveReviewProduct(@RequestParam("productId") Long productId, @RequestParam("rating") int rating,
                                               @RequestParam("description") String description, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            HttpSession session = request.getSession();
            CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
            if (customerDTO != null) {
                ProductReview productReview = new ProductReview();
                productReview.setCustomer(customerRepository.getOne(customerDTO.id()));
                productReview.setProduct(productRepository.getOne(productId));
                productReview.setRating(rating);
                productReview.setComment(description);
                LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
                productReview.setCreatedAt(currentDateTime);
                productReviewService.saveProductReview(productReview);
                return "true";
            } else {
                return "false";
            }
        } catch (ProductReviewException e) {
            return "false";
        }
    }
}
