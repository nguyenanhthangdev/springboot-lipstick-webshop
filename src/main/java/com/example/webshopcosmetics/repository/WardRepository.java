package com.example.webshopcosmetics.repository;

import com.example.webshopcosmetics.model.District;
import com.example.webshopcosmetics.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {
    @Query("SELECT w FROM Ward w WHERE w.district.district_id = :district_id")
    List<Ward> findAllWardByDistrictId(@Param("district_id") Long district_id);
}