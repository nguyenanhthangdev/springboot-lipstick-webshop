package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.dto.CustomerDTO;
import com.example.webshopcosmetics.model.*;
import com.example.webshopcosmetics.repository.ProductGalleryRepository;
import com.example.webshopcosmetics.service.banner.BannerService;
import com.example.webshopcosmetics.service.category.CategoryService;
import com.example.webshopcosmetics.service.customer.CustomerService;
import com.example.webshopcosmetics.service.manufacturer.ManufacturerService;
import com.example.webshopcosmetics.service.posts.PostsService;
import com.example.webshopcosmetics.service.product.ProductService;
import com.example.webshopcosmetics.service.productReview.ProductReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired private ProductService productService;
    @Autowired private BannerService bannerService;
    @Autowired private CategoryService categoryService;
    @Autowired private ManufacturerService manufacturerService;
    @Autowired private ProductGalleryRepository productGalleryRepository;
    @Autowired private ProductReviewService productReviewService;
    @Autowired private PostsService postsService;

    @GetMapping("/cosmetic")
    public String home(Model model, HttpServletRequest servletRequest, HttpServletRequest request) {
        model.addAttribute("products", productService.getAllActiveProduct());
        model.addAttribute("bannersBig", bannerService.getAllBannerBig());
        model.addAttribute("bannersSmall", bannerService.getAllBannerSmall());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("manufacturers", manufacturerService.getAllActiveManufacturers());
        model.addAttribute("posts", postsService.getAllActivePosts());
        return "client/home/home";
    }

    @GetMapping("/cosmetic/product-details")
    public String productDetails(Model model, @Param("id") Long id, HttpServletRequest request, HttpSession session) {
        Product product = productService.getOneProductById(id);
        List<ProductReview> listProductReviews = productReviewService.getAllProductReviewByProductId(id);
        model.addAttribute("product", product);
        model.addAttribute("galleries", productGalleryRepository.findAllGalleyByProductId(id));
        model.addAttribute("manufacturer", manufacturerService.getOneManufacturer(product.getManufacturer().getManufacturer_id()));
        model.addAttribute("product_options", productService.getAllProductOptionsByProductId(id));
        model.addAttribute("product_reviews", listProductReviews);
        double numberOfFiveStarReviews = 0; double numberOfFourStarReviews = 0; double numberOfThreeStarReviews = 0; double numberOfTwoStarReviews = 0; double numberOfOneStarReviews = 0; double averageRating = 0;
        for (ProductReview productReview : listProductReviews) {
            averageRating = averageRating + productReview.getRating();
            if (productReview.getRating() == 1) {numberOfOneStarReviews ++;}
            if (productReview.getRating() == 2) {numberOfTwoStarReviews ++;}
            if (productReview.getRating() == 3) {numberOfThreeStarReviews ++;}
            if (productReview.getRating() == 4) {numberOfFourStarReviews ++;}
            if (productReview.getRating() == 5) {numberOfFiveStarReviews ++;}
        }
        averageRating = averageRating/listProductReviews.size();
        averageRating = Math.round(averageRating * 10.0) / 10.0;
        int integerPart = (int) averageRating;
        int fractionalPart = (int)((averageRating - integerPart)*100);
        model.addAttribute("numberOfOneStarReviews", numberOfOneStarReviews);
        model.addAttribute("numberOfTwoStarReviews", numberOfTwoStarReviews);
        model.addAttribute("numberOfThreeStarReviews", numberOfThreeStarReviews);
        model.addAttribute("numberOfFourStarReviews", numberOfFourStarReviews);
        model.addAttribute("numberOfFiveStarReviews", numberOfFiveStarReviews);
        model.addAttribute("averageRating", averageRating);
        model.addAttribute("integerPart", integerPart);
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("fractionalPart", fractionalPart);
        CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("s_customer");
        if (customerDTO != null) {
            model.addAttribute("customerName", customerDTO.fullName());
        } else {
            model.addAttribute("customerName", "");
        }
        return "client/home/product-details";
    }


}
