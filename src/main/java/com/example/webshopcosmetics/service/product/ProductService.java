package com.example.webshopcosmetics.service.product;

import com.example.webshopcosmetics.model.Product;
import com.example.webshopcosmetics.model.ProductOptions;
import com.example.webshopcosmetics.model.ProductReview;
import com.example.webshopcosmetics.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public List<Product> searchProductByName(String keyWord);
    public Page<Product> getAllProduct(int pageNo, int size);
    public Page<Product> getAllProduct(int pageNo, String keyword, int size, int status, int sort);
    public Product saveProduct(Product product);
    public Product updateProduct(Product product);
    public ProductOptions saveProductOptions(ProductOptions productOptions);
    public List<ProductOptions> getAllProductOptionsByProductId(Long product_id);
    public Product getOneProductById(Long id);
    public void deleteProduct(Long id);
    public void deleteAllProductOptionsByProductId(Long id);
    public ProductOptions getOneProductOptionsById(Long id);
    public ProductOptions getProductOptionsById(Long id);
    public ProductOptions getAllProductOptionsById(Long id);
    //-----------------------------------------HOME-------------------------------------------//
    public List<Product> getAllActiveProduct();
    List<Product> searchAllProductByName(String keyword);
    List<Product> getAllProductByCategoryId(Long id);
    List<Product> getAllProductByManufacturerId(Long id);

}
