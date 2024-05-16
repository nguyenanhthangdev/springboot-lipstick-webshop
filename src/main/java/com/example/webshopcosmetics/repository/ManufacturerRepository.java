package com.example.webshopcosmetics.repository;

import com.example.webshopcosmetics.model.Category;
import com.example.webshopcosmetics.model.Manufacturer;
import com.example.webshopcosmetics.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    @Query("SELECT m FROM Manufacturer m WHERE m.name LIKE %:keyword%")
    List<Manufacturer> searchManufacturerByName(String keyword);

    Page<Manufacturer> findByNameContaining(String keyword, Pageable pageable);

    Page<Manufacturer> findByStatusAndNameContaining(Boolean status, String name, Pageable pageable);

    //===========HOME==========//

    @Query("SELECT c FROM Manufacturer c WHERE c.status = true")
    List<Manufacturer> findAllActiveManufacturers();
}
