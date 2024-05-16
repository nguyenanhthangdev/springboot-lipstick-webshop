package com.example.webshopcosmetics.service.websiteSetting;

import com.example.webshopcosmetics.exception.CategoryException;
import com.example.webshopcosmetics.exception.WebsiteSettingException;
import com.example.webshopcosmetics.model.WebsiteSetting;
import com.example.webshopcosmetics.repository.WebsiteSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsiteSettingServiceImpl implements WebsiteSettingService{

    @Autowired
    private WebsiteSettingRepository websiteSettingRepository;

    @Override
    public List<WebsiteSetting> getAllWebsiteSetting() {
        return websiteSettingRepository.findAll();
    }

    @Override
    public WebsiteSetting saveWebsiteSetting(WebsiteSetting websiteSetting) {
        try {
            return websiteSettingRepository.save(websiteSetting);
        } catch (Exception e) {
            // Xử lý ngoại lệ khi xóa không thành công và ném ra CategoryDeleteException
            throw new WebsiteSettingException("Thay đổi thông tin hệ thống không thành công", e);
        }
    }

    @Override
    public void deleteAllWebsiteSetting() {
        try {
            websiteSettingRepository.deleteAll();
        } catch (Exception e) {
            // Xử lý ngoại lệ khi xóa không thành công và ném ra CategoryDeleteException
            throw new WebsiteSettingException("Thay đổi thông tin hệ thống không thành công", e);
        }
    }
}
