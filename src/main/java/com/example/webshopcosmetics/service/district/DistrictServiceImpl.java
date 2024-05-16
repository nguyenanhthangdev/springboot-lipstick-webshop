package com.example.webshopcosmetics.service.district;

import com.example.webshopcosmetics.model.City;
import com.example.webshopcosmetics.model.District;
import com.example.webshopcosmetics.model.Ward;
import com.example.webshopcosmetics.repository.CityRepository;
import com.example.webshopcosmetics.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService{
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<District> getAllDistrict() {
        return districtRepository.findAll();
    }

    @Override
    public List<District> getAllDistrictByCityId(Long city_id) {
        return districtRepository.findAllDistrictByCityId(city_id);
    }

    @Override
    public District getDistrictById(Long districtId) {
        return districtRepository.getOne(districtId);
    }


}
