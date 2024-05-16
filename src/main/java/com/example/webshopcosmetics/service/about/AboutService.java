package com.example.webshopcosmetics.service.about;

import com.example.webshopcosmetics.model.About;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AboutService {
    List<About> getAllABout();

    About getOneAboutById(Long about_id);

    About saveAbout(About about);
}
