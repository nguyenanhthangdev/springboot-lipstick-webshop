package com.example.webshopcosmetics.repository;

import com.example.webshopcosmetics.model.Banner;
import com.example.webshopcosmetics.model.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {
    @Query("SELECT b FROM Banner b WHERE b.option = com.example.webshopcosmetics.model.BannerOption.BIG")
    public List<Banner> getAllBannerBig();
    @Query("SELECT b FROM Banner b WHERE b.option = com.example.webshopcosmetics.model.BannerOption.SMALL")
    public List<Banner> getAllBannerSmall();
}
