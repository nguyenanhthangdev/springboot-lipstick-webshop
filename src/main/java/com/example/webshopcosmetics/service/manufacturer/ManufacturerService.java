package com.example.webshopcosmetics.service.manufacturer;

import com.example.webshopcosmetics.model.Manufacturer;
import com.example.webshopcosmetics.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ManufacturerService {
    public List<Manufacturer> searchManufacturerByName(String keyword);

    public Page<Manufacturer> getAllManufacturer(int pageNoString, String keyword, int size, int status);

    public List<Manufacturer> getAllManufacturer();

    public Manufacturer getOneManufacturer(Long id);

    public Manufacturer saveManufacturer(Manufacturer manufacturer);

    public Manufacturer updateManufacturer(Manufacturer manufacturer);

    public void deleteManufacturer(Long id);

    public List<Manufacturer> getAllActiveManufacturers();
}
