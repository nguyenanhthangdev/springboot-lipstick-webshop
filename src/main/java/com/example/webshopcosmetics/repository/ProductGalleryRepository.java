package com.example.webshopcosmetics.repository;

import com.example.webshopcosmetics.model.ProductGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductGalleryRepository extends JpaRepository<ProductGallery, Long> {
    @Query("SELECT g FROM ProductGallery g WHERE g.product.product_id = :product_id")
    public List<ProductGallery> findAllGalleyByProductId(Long product_id);

    @Modifying
    @Query("DELETE FROM ProductGallery g WHERE g.product.product_id = :product_id")
    void deleteAllGalleryByProductId(Long product_id);
}
