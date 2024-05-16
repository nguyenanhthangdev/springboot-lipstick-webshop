package com.example.webshopcosmetics.service.productReview;

import com.example.webshopcosmetics.model.Product;
import com.example.webshopcosmetics.model.ProductOptions;
import com.example.webshopcosmetics.model.ProductReview;
import com.example.webshopcosmetics.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductReviewService {
    public ProductReview saveProductReview(ProductReview productReview);
    public List<ProductReview> getAllProductReviewByProductId(Long productId);
    public ProductReview checkCustomerReviewProduct(Long CustomerId, Long ProductId);
}
