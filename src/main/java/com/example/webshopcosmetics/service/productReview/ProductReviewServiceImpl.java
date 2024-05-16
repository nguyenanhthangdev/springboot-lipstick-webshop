package com.example.webshopcosmetics.service.productReview;

import com.example.webshopcosmetics.exception.ProductException;
import com.example.webshopcosmetics.model.Product;
import com.example.webshopcosmetics.model.ProductReview;
import com.example.webshopcosmetics.repository.CustomerRepository;
import com.example.webshopcosmetics.repository.ProductRepository;
import com.example.webshopcosmetics.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class ProductReviewServiceImpl implements ProductReviewService{
    @Autowired private ProductReviewRepository productReviewRepository;
    @Override
    public ProductReview saveProductReview(ProductReview productReview) {
        try {
            return productReviewRepository.save(productReview);
        } catch (Exception e) {
            throw new ProductException("Đánh giá sản phẩm không thành công", e);
        }
    }
    @Override
    public List<ProductReview> getAllProductReviewByProductId(Long productId) {
        List<ProductReview> listProductReview = productReviewRepository.getAllProductReviewByProductId(productId);
        return listProductReview;
    }
    @Override
    public ProductReview checkCustomerReviewProduct(Long customerId, Long productId) {
        return productReviewRepository.checkCustomerReviewProduct(customerId, productId);
    }
}
