package com.example.webshopcosmetics.repository;

import com.example.webshopcosmetics.model.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    @Query("SELECT pr FROM ProductReview pr WHERE pr.product.product_id = :productId ORDER BY pr.createdAt DESC")
    public List<ProductReview> getAllProductReviewByProductId(Long productId);

    @Query("SELECT pr FROM ProductReview pr WHERE pr.product.product_id = :productId AND pr.customer.customer_id = :customerId")
    public ProductReview checkCustomerReviewProduct(Long customerId, Long productId);
}
