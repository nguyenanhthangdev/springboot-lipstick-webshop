package com.example.webshopcosmetics.service.websiteSetting;

import com.example.webshopcosmetics.model.WebsiteSetting;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WebsiteSettingService {
    public List<WebsiteSetting> getAllWebsiteSetting();

    public WebsiteSetting saveWebsiteSetting(WebsiteSetting websiteSetting);

    public void deleteAllWebsiteSetting();
}
