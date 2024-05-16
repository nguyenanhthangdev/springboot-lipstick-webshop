package com.example.webshopcosmetics.repository;

import com.example.webshopcosmetics.model.Category;
import com.example.webshopcosmetics.model.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT m FROM Manufacturer m WHERE m.name LIKE %:keyword%")
    List<Category> searchCategoryByName(String keyword);

    Page<Category> findByNameContaining(String keyword, Pageable pageable);

    Page<Category> findByStatusAndNameContaining(Boolean status, String name, Pageable pageable);
}
