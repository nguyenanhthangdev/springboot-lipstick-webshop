package com.example.webshopcosmetics.service.city;

import com.example.webshopcosmetics.model.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {
    public List<City> getAllCity();

    public City getCityById(Long cityId);
}
