package com.example.webshopcosmetics.service.product;

import com.example.webshopcosmetics.exception.CategoryException;
import com.example.webshopcosmetics.exception.ProductException;
import com.example.webshopcosmetics.exception.ProductOptionsException;
import com.example.webshopcosmetics.model.Product;
import com.example.webshopcosmetics.model.ProductGallery;
import com.example.webshopcosmetics.model.ProductOptions;
import com.example.webshopcosmetics.repository.ProductOptionsRepository;
import com.example.webshopcosmetics.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

import java.text.NumberFormat;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductOptionsRepository productOptionsRepository;

    @Override
    public List<Product> searchProductByName(String keyword) {
        return productRepository.searchProductByName(keyword);
    }

    @Override
    public Page<Product> getAllProduct(int pageNo, int size) {
        try {
            return productRepository.findAll(PageRequest.of(pageNo - 1, size));
        } catch (Exception e) {
            throw new ProductException("Lấy tất cả sản phẩm không thành công", e);
        }
    }

    @Override
    public Page<Product> getAllProduct(int pageNo, String keyword, int size, int status, int sort_price) {
        if (keyword==null) {
            keyword = "";
        } else {
            keyword = keyword.trim();
        }
        if (productRepository.count() <= size) {
            pageNo = 1;
        }

        Sort sort = null;
        Pageable pageable = null;
        if (sort_price == 1) {
            sort = Sort.by("price").descending(); // Sắp xếp giảm dần theo giá tiền
            pageable = PageRequest.of(pageNo - 1, size, sort);
        } else if (sort_price == 2) {
            sort = Sort.by("price").ascending(); // Sắp xếp tăng dần theo giá tiền
            pageable = PageRequest.of(pageNo - 1, size, sort);
        } else if (sort_price == 0) {
            sort = Sort.by("createdAt").descending();
            pageable = PageRequest.of(pageNo - 1, size, sort);
        }

        if (status == 2) {
            return productRepository.findByNameContaining(keyword, pageable);
        } else {
            boolean boolStatus = false;
            if (status == 1) {
                boolStatus = true;
            }
            // Trường hợp status=true hoặc status=false
            return productRepository.findByStatusAndNameContaining(boolStatus, keyword, pageable);
        }
    }

    @Override
    public Product saveProduct(Product product) {
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            throw new ProductException("Thêm sản phẩm không thành công", e);
        }
    }

    @Override
    public Product updateProduct(Product product) {
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            throw new ProductException("Thay đổi thông tin sản phẩm không thành công", e);
        }
    }

    @Override
    public ProductOptions saveProductOptions(ProductOptions productOptions) {
        try {
            return productOptionsRepository.save(productOptions);
        } catch (Exception e) {
            throw new ProductOptionsException("Lưu sản phẩm không thành công", e);
        }
    }

    @Override
    public List<ProductOptions> getAllProductOptionsByProductId(Long product_id) {
        try {
            return productOptionsRepository.findAllProductOptionsByProductId(product_id);
        } catch (Exception e) {
            throw new ProductOptionsException("Lấy tất cả sản phẩm không thành công", e);
        }
    }

    @Override
    public Product getOneProductById(Long id) {
        try {
            Optional<Product> productOption = productRepository.findById(id);
            Product product = productOption.orElse(null);
            return product;
        } catch (Exception e) {
            throw new ProductException("Sản phẩm không tồn tại", e);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            // Xử lý ngoại lệ khi xóa không thành công và ném ra CategoryDeleteException
            throw new ProductException("Xóa sản phẩm không thành công", e);
        }
    }

    @Override
    public void deleteAllProductOptionsByProductId(Long product_id) {
        try {
            List<ProductOptions> productOptions = productOptionsRepository.findAllProductOptionsByProductId(product_id);
            productOptionsRepository.deleteAll(productOptions);
        } catch (Exception e) {
            throw new ProductException("Thay đổi thông tin sản phẩm không tành công", e);
        }
    }

    @Override
    public ProductOptions getOneProductOptionsById(Long id) {
        return productOptionsRepository.getOne(id);
    }

    @Override
    public ProductOptions getProductOptionsById(Long id) {
        try {
            Optional<ProductOptions> productOption = productOptionsRepository.findById(id);
            ProductOptions product_o = productOption.orElse(null);
            return product_o;
        } catch (Exception e) {
            throw new ProductOptionsException("Loại màu sản phẩm không tồn tại", e);
        }
    }

    @Override
    public ProductOptions getAllProductOptionsById(Long id) {
        System.out.println("id: " + id);
        try {
            List<ProductOptions> productOptions = productOptionsRepository.findProductOptionsByProductId(id);
            if (productOptions.isEmpty() && productOptions.size() > 0) {
                return null;
            } else {
                return productOptions.get(0);
            }
        } catch (Exception e) {
            throw new ProductOptionsException("Loại màu sản phẩm không tồn tại", e);
        }
    }

    //================HOME===============//

    @Override
    public List<Product> getAllActiveProduct() {
        return productRepository.findAllActiveProducts();
    }

    public static String formatNumberToVND(BigDecimal price) {
        NumberFormat currentLocale = NumberFormat.getInstance();

        // tạo 1 NumberFormat để định dạng số theo tiêu chuẩn của nước Anh
        Locale localeEN = new Locale("en", "EN");
        NumberFormat nf = NumberFormat.getInstance(localeEN);

        // đối với số có kiểu long được định dạng theo chuẩn của nước Anh
        // thì phần ngàn của số được phân cách bằng dấu phẩy
        String priceAfterFormat = nf.format(price) + " đ";
        return priceAfterFormat;
    }
    @Override
    public List<Product> searchAllProductByName(String keyword) {
        return productRepository.findByProductNameContainingIgnoreCase(keyword);
    }
    @Override
    public List<Product> getAllProductByCategoryId(Long id) {
        return productRepository.findAllProductByCategoryId(id);
    }
    @Override
    public List<Product> getAllProductByManufacturerId(Long id) {
        return productRepository.findAllProductByManufacturerId(id);
    }
}
