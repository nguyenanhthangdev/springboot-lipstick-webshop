package com.example.webshopcosmetics.repository;

import com.example.webshopcosmetics.model.ProductGallery;
import com.example.webshopcosmetics.model.ProductOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductOptionsRepository extends JpaRepository<ProductOptions, Long> {

    @Query("SELECT g FROM ProductOptions g WHERE g.product.product_id = :product_id")
    public List<ProductOptions> findAllProductOptionsByProductId(Long product_id);

    @Modifying
    @Query("DELETE FROM ProductOptions p WHERE p.product.product_id = :product_id")
    void deleteAllProductOptionsByProductId(Long product_id);

    @Modifying
    @Transactional
    @Query("UPDATE ProductOptions po SET po.amount = 0 WHERE po.id = :id")
    void updateAmountToZero(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE ProductOptions po SET po.amount = :newAmount WHERE po.id = :id")
    void updateAmount(Long id, int newAmount);

    @Query("SELECT po FROM ProductOptions po WHERE po.product.product_id = :productId")
    List<ProductOptions> findProductOptionsByProductId(Long productId);
}
