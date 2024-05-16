package com.example.webshopcosmetics.repository;

import com.example.webshopcosmetics.model.WebsiteSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsiteSettingRepository extends JpaRepository<WebsiteSetting, Long> {
}
