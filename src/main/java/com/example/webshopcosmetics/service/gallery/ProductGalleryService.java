package com.example.webshopcosmetics.service.gallery;

import com.example.webshopcosmetics.model.ProductGallery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductGalleryService {

    public ProductGallery saveGalleryByProductId(ProductGallery productGallery);

    public List<ProductGallery> getAllGalleyByProductId(Long product_id);

    public void deleteAllGalleryByProductId(Long product_id);
}
