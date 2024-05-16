package com.example.webshopcosmetics.repository;

import com.example.webshopcosmetics.model.City;
import com.example.webshopcosmetics.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    @Query("SELECT d FROM District d WHERE d.city.city_id = :city_id")
    List<District> findAllDistrictByCityId(@Param("city_id") Long city_id);
}
