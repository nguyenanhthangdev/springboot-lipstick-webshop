package com.example.webshopcosmetics.service.ward;

import com.example.webshopcosmetics.model.District;
import com.example.webshopcosmetics.model.Ward;
import com.example.webshopcosmetics.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardServiceImpl implements WardService {
    @Autowired
    private WardRepository wardRepository;

    @Override
    public List<Ward> getAllWardByDistrictId(Long district_id) {
        return wardRepository.findAllWardByDistrictId(district_id);
    }

    @Override
    public List<Ward> getAllWard() {
        return wardRepository.findAll();
    }

    @Override
    public Ward getWardById(Long ward_id) {
        return wardRepository.getOne(ward_id);
    }
}
