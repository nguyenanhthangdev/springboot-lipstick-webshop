package com.example.webshopcosmetics.repository;

import com.example.webshopcosmetics.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    @Query("SELECT d FROM Product d WHERE d.name LIKE %:keyword%")
    List<Product> searchProductByName(String keyword);
    Page<Product> findByNameContaining(String keyword, Pageable pageable);
    Page<Product> findByStatusAndNameContaining(Boolean status, String name, Pageable pageable);
    //-----------------------------------------HOME-------------------------------------------//
    @Query("SELECT p FROM Product p WHERE p.status = true")
    List<Product> findAllActiveProducts();
    @Query(value = "SELECT * FROM product p WHERE LOWER(name) LIKE LOWER(CONCAT('%', :keyword, '%')) AND p.status = true", nativeQuery = true)
    List<Product> findByProductNameContainingIgnoreCase(String keyword);
    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.category_id = :categoryId AND p.status = true")
    List<Product> findAllProductByCategoryId(@Param("categoryId") Long categoryId);
    @Query("SELECT p FROM Product p WHERE p.manufacturer.manufacturer_id = :manufacturerId AND p.status = true")
    List<Product> findAllProductByManufacturerId(Long manufacturerId);

}
