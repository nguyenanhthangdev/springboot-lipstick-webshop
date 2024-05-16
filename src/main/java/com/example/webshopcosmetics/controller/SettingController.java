package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.exception.ProductException;
import com.example.webshopcosmetics.exception.WebsiteSettingException;
import com.example.webshopcosmetics.model.Product;
import com.example.webshopcosmetics.model.ProductGallery;
import com.example.webshopcosmetics.model.WebsiteSetting;
import com.example.webshopcosmetics.service.websiteSetting.WebsiteSettingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
public class SettingController {

    @Autowired
    private WebsiteSettingService websiteSettingService;

    @GetMapping("/admin/setting")
    public String getAllSetting(Model model) {
        List<WebsiteSetting> list = websiteSettingService.getAllWebsiteSetting();
        model.addAttribute("active_setting", "ACTIVE");
        model.addAttribute("websiteSettings", list);
        int listSize = list.size();
        if (listSize > 0) {
            if (list.get(0) != null) {
                model.addAttribute("name", list.get(0).getValue());
            } else {
                model.addAttribute("name", "");
            }
            if (listSize > 1 && list.get(1) != null) {
                model.addAttribute("address", list.get(1).getValue());
            } else {
                model.addAttribute("address", "");
            }
            if (listSize > 2 && list.get(2) != null) {
                model.addAttribute("google_map_embed", list.get(2).getValue());
            } else {
                model.addAttribute("google_map_embed", "");
            }
            if (listSize > 3 && list.get(3) != null) {
                model.addAttribute("email", list.get(3).getValue());
            } else {
                model.addAttribute("email", "");
            }
            if (listSize > 4 && list.get(4) != null) {
                model.addAttribute("phone", list.get(4).getValue());
            } else {
                model.addAttribute("phone", "");
            }
            if (listSize > 5 && list.get(5) != null) {
                model.addAttribute("image_logo", list.get(5).getValue());
            } else {
                model.addAttribute("image_logo", "");
            }
            if (listSize > 6 && list.get(6) != null) {
                model.addAttribute("image_favicon", list.get(6).getValue());
            } else {
                model.addAttribute("image_favicon", "");
            }
        }
        return "admin/setting/all-setting";
    }

    @PostMapping("/admin/setting")
    public String saveAllSetting(Model model, @RequestParam("name") String name, @RequestParam("address") String address, @RequestParam("phone") String phone,
                                 @RequestParam("google_map_embed") String google_map_embed, @RequestParam("email") String email,
                                 @RequestParam("imageLogo") MultipartFile imageLogo, @RequestParam(value = "imageLogoOld") String imageLogoOld,
                                 @RequestParam("imageFavicon") MultipartFile imageFavicon, @RequestParam(value = "imageFaviconOld") String imageFaviconOld,
                                 RedirectAttributes redirectAttributes, HttpSession session) {

        redirectAttributes.addFlashAttribute("editType", "websiteSetting");
        try {
            websiteSettingService.deleteAllWebsiteSetting();

            WebsiteSetting websiteSetting = new WebsiteSetting();
            websiteSetting.setKey("name");
            websiteSetting.setValue(name.trim());
            websiteSettingService.saveWebsiteSetting(websiteSetting);

            WebsiteSetting websiteSetting1 = new WebsiteSetting();
            websiteSetting1.setKey("address");
            websiteSetting1.setValue(address.trim());
            websiteSettingService.saveWebsiteSetting(websiteSetting1);

            WebsiteSetting websiteSetting2 = new WebsiteSetting();
            websiteSetting2.setKey("google_map_embed");
            websiteSetting2.setValue(google_map_embed);
            websiteSettingService.saveWebsiteSetting(websiteSetting2);

            WebsiteSetting websiteSetting3 = new WebsiteSetting();
            websiteSetting3.setKey("email");
            websiteSetting3.setValue(email.trim());
            websiteSettingService.saveWebsiteSetting(websiteSetting3);

            WebsiteSetting websiteSetting4 = new WebsiteSetting();
            websiteSetting4.setKey("phone");
            websiteSetting4.setValue(phone.trim());
            websiteSettingService.saveWebsiteSetting(websiteSetting4);

            if (!imageLogo.isEmpty()){
                String uniqueString = UUID.randomUUID().toString();
                String fileName = uniqueString + "-" + imageLogo.getOriginalFilename();
                byte[] bytes = imageLogo.getBytes();
                Path path = Paths.get("src/main/resources/static/images/logo/" + fileName);
                Files.write(path, bytes);

//                Xóa ảnh cũ
                if (!imageLogoOld.isEmpty()) {
                    Files.deleteIfExists(Paths.get("src/main/resources/static/images/logo/" + imageLogoOld));
                }
                WebsiteSetting websiteSetting5 = new WebsiteSetting();
                websiteSetting5.setKey("image_logo");
                websiteSetting5.setValue(fileName);
                websiteSettingService.saveWebsiteSetting(websiteSetting5);
            } else {
                WebsiteSetting websiteSetting5 = new WebsiteSetting();
                websiteSetting5.setKey("image_logo");
                websiteSetting5.setValue(imageLogoOld);
                websiteSettingService.saveWebsiteSetting(websiteSetting5);
            }
            if (!imageFavicon.isEmpty()){
                String uniqueString = UUID.randomUUID().toString();
                String fileName = uniqueString + "-" + imageFavicon.getOriginalFilename();
                byte[] bytes = imageFavicon.getBytes();
                Path path = Paths.get("src/main/resources/static/images/logo/" + fileName);
                Files.write(path, bytes);

//                Xóa ảnh cũ
                if (!imageFaviconOld.isEmpty()) {
                    Files.deleteIfExists(Paths.get("src/main/resources/static/images/logo/" + imageFaviconOld));
                }
                WebsiteSetting websiteSetting6 = new WebsiteSetting();
                websiteSetting6.setKey("image_favicon");
                websiteSetting6.setValue(fileName);
                websiteSettingService.saveWebsiteSetting(websiteSetting6);
            } else {
                WebsiteSetting websiteSetting6 = new WebsiteSetting();
                websiteSetting6.setKey("image_favicon");
                websiteSetting6.setValue(imageFaviconOld);
                websiteSettingService.saveWebsiteSetting(websiteSetting6);
            }
            Object webshopSettings = session.getAttribute("webshopSettings");
            session.setAttribute("webshopSettings", websiteSettingService.getAllWebsiteSetting());
            redirectAttributes.addFlashAttribute("successMessage", "Thay đổi thông tin hệ thống thành công");
            return "redirect:/admin/setting";
        } catch (WebsiteSettingException | IOException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Thay đổi thông tin hệ thống không thành công");
            return "redirect:/admin/setting";
        }
    }
}
