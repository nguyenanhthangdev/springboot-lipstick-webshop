package com.example.webshopcosmetics.service.manufacturer;

import com.example.webshopcosmetics.exception.CategoryException;
import com.example.webshopcosmetics.exception.ManufacturerException;
import com.example.webshopcosmetics.model.Manufacturer;
import com.example.webshopcosmetics.model.Product;
import com.example.webshopcosmetics.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService{
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public List<Manufacturer> searchManufacturerByName(String keyword) {
        return manufacturerRepository.searchManufacturerByName(keyword);
    }

    @Override
    public Page<Manufacturer> getAllManufacturer(int pageNo, String keyword, int size, int status) {
        if (keyword==null) {
            keyword = "";
        } else {
            keyword = keyword.trim();
        }
        if (manufacturerRepository.count() <= size) {
            pageNo = 1;
        }

        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(pageNo - 1, size, sort);
        if (status == 2) {
            // Trường hợp status=null, không lọc theo status
            return manufacturerRepository.findByNameContaining(keyword, pageable);
        } else {
            boolean boolStatus = false;
            if (status == 1) {
                boolStatus = true;
            }
            // Trường hợp status=true hoặc status=false
            return manufacturerRepository.findByStatusAndNameContaining(boolStatus, keyword, pageable);
        }
    }

    @Override
    public List<Manufacturer> getAllManufacturer() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer getOneManufacturer(Long id) {
        try {
            return manufacturerRepository.getOne(id);
        } catch (ManufacturerException e) {
            throw new ManufacturerException("Thương hiệu sản phẩm không tồn tại", e);
        }
    }

    @Override
    public Manufacturer saveManufacturer(Manufacturer manufacturer) {
        try {
            return manufacturerRepository.save(manufacturer);
        } catch (ManufacturerException e) {
            throw new ManufacturerException("Thêm thương hiệu sản phẩm không thành công", e);
        }
    }

    @Override
    public Manufacturer updateManufacturer(Manufacturer manufacturer) {
        try {
            return manufacturerRepository.save(manufacturer);
        } catch (ManufacturerException e) {
            throw new ManufacturerException("Thay đổi thông tin thương hiệu sản phẩm không thành công", e);
        }
    }

    @Override
    public void deleteManufacturer(Long id) {
        try {
            manufacturerRepository.deleteById(id);
        } catch (Exception e) {
            // Xử lý ngoại lệ khi xóa không thành công và ném ra CategoryDeleteException
            throw new ManufacturerException("Xóa thương hiệu sản phẩm không thành công", e);
        };
    }

    @Override
    public List<Manufacturer> getAllActiveManufacturers() {
        return manufacturerRepository.findAllActiveManufacturers();
    }
}
