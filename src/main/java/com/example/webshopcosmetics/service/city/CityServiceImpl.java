package com.example.webshopcosmetics.service.city;

import com.example.webshopcosmetics.model.City;
import com.example.webshopcosmetics.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService{
    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    @Override
    public City getCityById(Long cityId) {
        return cityRepository.getOne(cityId);
    }
}
