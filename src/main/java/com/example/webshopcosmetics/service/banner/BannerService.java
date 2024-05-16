package com.example.webshopcosmetics.service.banner;

import com.example.webshopcosmetics.model.Banner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BannerService {
    public List<Banner> getAllBanner();

    public List<Banner> getAllBannerBig();

    public List<Banner> getAllBannerSmall();

    public Banner getOneBanner(Long id);

    public Banner saveBanner(Banner banner);

    public void deleteBanner(Long id);
}
