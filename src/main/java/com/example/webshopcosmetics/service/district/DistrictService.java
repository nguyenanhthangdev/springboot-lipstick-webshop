package com.example.webshopcosmetics.service.district;

import com.example.webshopcosmetics.model.City;
import com.example.webshopcosmetics.model.District;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DistrictService {
    public List<District> getAllDistrict();

    public List<District> getAllDistrictByCityId(Long city_id);

    public District getDistrictById(Long district_id);

}
