package com.example.webshopcosmetics.service.banner;

import com.example.webshopcosmetics.exception.BannerException;
import com.example.webshopcosmetics.exception.CategoryException;
import com.example.webshopcosmetics.model.Banner;
import com.example.webshopcosmetics.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService{
    @Autowired
    private BannerRepository bannerRepository;

    public List<Banner> getAllBanner() {
        return bannerRepository.findAll();
    }

    @Override
    public List<Banner> getAllBannerBig() {
        return bannerRepository.getAllBannerBig();
    }
    @Override
    public List<Banner> getAllBannerSmall() {
        List<Banner> allBanners = bannerRepository.getAllBannerSmall();
        int bannerCount = allBanners.size();

        if (bannerCount == 1) {
            return allBanners.subList(0, 1); // Chỉ lấy 1 phần tử
        } else if (bannerCount >= 2) {
            return allBanners.subList(0, 2); // Lấy 2 phần tử đầu tiên
        } else {
            return allBanners; // Trường hợp không có banner nào
        }
    }

    @Override
    public Banner getOneBanner(Long id) {
        return bannerRepository.getOne(id);
    }

    @Override
    public Banner saveBanner(Banner banner) {
        try {
            return bannerRepository.save(banner);
        } catch (Exception e) {
            // Xử lý ngoại lệ khi xóa không thành công và ném ra BannerException
            throw new BannerException("Thêm ảnh banner không thành công", e);
        }
    }

    @Override
    public void deleteBanner(Long id) {
        try {
            bannerRepository.deleteById(id);
        } catch (Exception e) {
            // Xử lý ngoại lệ khi xóa không thành công và ném ra BannerException
            throw new CategoryException("Xóa ảnh banner không thành công", e);
        }
    }
}
