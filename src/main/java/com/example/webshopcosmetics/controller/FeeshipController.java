package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.model.City;
import com.example.webshopcosmetics.model.District;
import com.example.webshopcosmetics.model.Ward;
import com.example.webshopcosmetics.service.city.CityService;
import com.example.webshopcosmetics.service.district.DistrictService;
import com.example.webshopcosmetics.service.ward.WardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FeeshipController {
    @Autowired
    private CityService cityService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private WardService wardService;

    @GetMapping("/admin/feeship")
    public String getAllFreeship(Model model) {
        model.addAttribute("active_feeship", "ACTIVE");
        return "admin/feeship/all-feeship";
    }

    @GetMapping("/admin/feeship/add-feeship")
    public String addFeeship(Model model) {
        model.addAttribute("active_feeship", "ACTIVE");
        model.addAttribute("cities", cityService.getAllCity());
        return "admin/feeship/add-feeship";
    }

    @GetMapping("/get-all-district-by-city-id")
    @ResponseBody
    public List<District> getAllDistrictByCityId(@RequestParam(value = "city_id", required = true) Long city_id) {
        return districtService.getAllDistrictByCityId(city_id);
    }

    @GetMapping("/get-all-ward-by-district-id")
    @ResponseBody
    public List<Ward> getAllWardByDistrictId(@RequestParam(value = "district_id", required = true) Long district_id) {
        return wardService.getAllWardByDistrictId(district_id);
    }

    @GetMapping("/cosmetic/get-all-city")
    @ResponseBody
    public List<City> getAllCity(HttpSession session) {
        return cityService.getAllCity();
    }
}
