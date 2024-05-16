package com.example.webshopcosmetics.service.ward;

import com.example.webshopcosmetics.model.District;
import com.example.webshopcosmetics.model.Ward;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WardService {
    public List<Ward> getAllWardByDistrictId(Long district_id);

    public List<Ward> getAllWard();

    public Ward getWardById(Long ward_id);
}
