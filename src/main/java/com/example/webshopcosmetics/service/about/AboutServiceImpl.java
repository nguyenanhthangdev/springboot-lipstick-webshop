package com.example.webshopcosmetics.service.about;

import com.example.webshopcosmetics.exception.AboutException;
import com.example.webshopcosmetics.exception.CategoryException;
import com.example.webshopcosmetics.model.About;
import com.example.webshopcosmetics.repository.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AboutServiceImpl implements AboutService{
    @Autowired private AboutRepository aboutRepository;

    @Override
    public List<About> getAllABout() {
        try {
            return aboutRepository.findAll();
        } catch (Exception e) {
            throw new AboutException("Thay đổi thông tin thất bại", e);
        }
    }

    @Override
    public About getOneAboutById(Long about_id) {
        try {
            return aboutRepository.getOne(about_id);
        } catch (Exception e) {
            throw new AboutException("Thay đổi thông tin thất bại", e);
        }
    }

    @Override
    public About saveAbout(About about) {
        try {
            return aboutRepository.save(about);
        } catch (Exception e) {
            throw new AboutException("Thay đổi thông tin thất bại", e);
        }
    }
}
