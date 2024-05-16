package com.example.webshopcosmetics.service.gallery;

import com.example.webshopcosmetics.model.ProductGallery;
import com.example.webshopcosmetics.repository.ProductGalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGalleryServiceImpl implements ProductGalleryService {
    @Autowired
    private ProductGalleryRepository productGalleryRepository;

    @Override
    public ProductGallery saveGalleryByProductId(ProductGallery productGallery) {
        if (productGallery != null) {
            return productGalleryRepository.save(productGallery);
        }
        return null;
    }

    @Override
    public List<ProductGallery> getAllGalleyByProductId(Long product_id) {
        return productGalleryRepository.findAllGalleyByProductId(product_id);
    }

    @Override
    public void deleteAllGalleryByProductId(Long product_id) {
        List<ProductGallery> galleries = productGalleryRepository.findAllGalleyByProductId(product_id);
        productGalleryRepository.deleteAll(galleries);
    }
}
