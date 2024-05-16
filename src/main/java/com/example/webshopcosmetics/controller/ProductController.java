package com.example.webshopcosmetics.controller;

import com.example.webshopcosmetics.exception.CategoryException;
import com.example.webshopcosmetics.exception.ProductException;
import com.example.webshopcosmetics.model.*;
import com.example.webshopcosmetics.service.category.CategoryService;
import com.example.webshopcosmetics.service.gallery.ProductGalleryService;
import com.example.webshopcosmetics.service.manufacturer.ManufacturerService;
import com.example.webshopcosmetics.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Controller
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductGalleryService productGalleryService;

    @GetMapping("/admin/product")
    public String getAllProduct(Model model, @Param("keyword") String keyword,
                                @RequestParam(name="size", defaultValue = "10") String sizeString, @RequestParam(value = "sort_price", defaultValue = "0") String sort_priceString,
                                @RequestParam(name="pageNo", defaultValue = "1") String pageNoString, @RequestParam(value = "status", defaultValue = "2") String statusString) {
        int pageNo = 1;
        try {
            pageNo = Integer.parseInt(pageNoString);
        } catch (NumberFormatException e) {
            pageNo = 1;
        }
        int status = 2;
        try {
            status = Integer.parseInt(statusString);
        } catch (NumberFormatException e) {
            status = 2;
        }
        int size = 10;
        try {
            size = Integer.parseInt(sizeString);
        } catch (NumberFormatException e) {
            size = 10;
        }
        int sort_price = 0;
        try {
            sort_price = Integer.parseInt(sort_priceString);
        } catch (NumberFormatException e) {
            sort_price = 0;
        }
        Page<Product> products = productService.getAllProduct(pageNo,keyword, size, status, sort_price);
        model.addAttribute("active_product", "ACTIVE");
        model.addAttribute("products", products);
        model.addAttribute("totalPage", products.getTotalPages());
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("keyword", keyword);
        model.addAttribute("size", size);
        model.addAttribute("status", status);
        model.addAttribute("sort_price", sort_price);
        return "admin/product/all-product";
    }

    @GetMapping("/admin/product/add-product")
    public String addProduct(Model model) {
        model.addAttribute("active_product", "ACTIVE");
        model.addAttribute("product", new Product());
        model.addAttribute("manufacturers", manufacturerService.getAllManufacturer());
        model.addAttribute("categories", categoryService.getAllCategoryHierarchy());
        return "admin/product/add-product";
    }

    @PostMapping("/admin/product/add-product")
    public String saveProduct(RedirectAttributes redirectAttributes, @RequestParam(value = "imageGallery", required = false) String[] imageGallery,
                              @RequestParam(value = "image") String image, @RequestParam("name") String name, @RequestParam("discountType") DiscountType discountType,
                              @RequestParam(value = "discountValue", defaultValue = "0") String discountValueString, @RequestParam("description") String description,
                              @RequestParam("status") boolean status, @RequestParam(value = "featured", required = false) boolean featured,
                              @RequestParam(value = "productNew", required = false) boolean productNew, @RequestParam(value = "best_seller", required = false) boolean best_seller,
                              @RequestParam("manufacturer") Manufacturer manufacturer, @RequestParam(value = "category", required = false) Category[] categories,
                              @RequestParam(value="options.image", required = false) String[] imageOptions, @RequestParam(value="options.name", required = false) String[] nameOptions,
                              @RequestParam(value="options.price", required = false) String[] priceOptions, @RequestParam(value="options.amount", required = false) int[] amountOptions) {
        try {
            Product product = new Product();
            product.setImage(image);
            product.setName(name);
//            BigDecimal price = new BigDecimal(priceString);
//            product.setPrice(price);
            if (imageOptions.length > 0) {
                BigDecimal price = new BigDecimal(priceOptions[0]);
                product.setPrice(price);
            } else {
                BigDecimal price = new BigDecimal(0);
                product.setPrice(price);
            }
            product.setDiscountType(discountType);
            BigDecimal discountValue = new BigDecimal(discountValueString);
            product.setDiscountValue(discountValue);
            product.setDescription(description);
            product.setStatus(status);
            product.setFeatured(featured);
            product.setProductNew(productNew);
            product.setBest_seller(best_seller);
            product.setManufacturer(manufacturer);
            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            product.setCreatedAt(currentDateTime);
            product.setUpdatedAt(currentDateTime);

            if (categories != null && categories.length > 0) {
               product.setCategories(Arrays.asList(categories));
            }

            Product saveProduct = productService.saveProduct(product);

            if (saveProduct != null) {
                if (imageGallery != null && imageGallery.length > 0) {
                    for (String img : imageGallery) {
                        ProductGallery gallery = new ProductGallery();
                        gallery.setProduct(saveProduct);
                        gallery.setImage(img);
                        productGalleryService.saveGalleryByProductId(gallery);
                    }
                }
            }

            if (saveProduct != null) {
                if (imageOptions != null && imageOptions.length > 0) {
                    for (int i = 0; i < imageOptions.length; i++) {
                        ProductOptions productOptions = new ProductOptions();
                        productOptions.setProduct(saveProduct);
                        productOptions.setImage(imageOptions[i]);
                        productOptions.setName(nameOptions[i]);
                        BigDecimal priceOption = new BigDecimal(priceOptions[i]);
                        productOptions.setPrice(priceOption);
                        productOptions.setAmount(amountOptions[i]);
                        productService.saveProductOptions(productOptions);
                    }
                }
            }

            String successMessage = "Thêm sản phẩm thành công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/product";
        } catch (ProductException e) {
            String errorMessage = "Thêm sản phẩm không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/product";
        }
    }

    @GetMapping("/admin/product/edit-product")
    public String editProduct(Model model, @Param("id") Long id, RedirectAttributes redirectAttributes) {
        model.addAttribute("active_product", "ACTIVE");
        Product product = productService.getOneProductById(id);
        if (product != null) {
            model.addAttribute("product", productService.getOneProductById(id));
            model.addAttribute("manufacturers", manufacturerService.getAllManufacturer());
            model.addAttribute("listCategory", categoryService.getAllCategoryHierarchy());
            model.addAttribute("galleries", productGalleryService.getAllGalleyByProductId(product.getProduct_id()));
            model.addAttribute("productOptions", productService.getAllProductOptionsByProductId(product.getProduct_id()));
            return "admin/product/edit-product";
        } else {
            String errorMessage = "Sản phẩn không tồn tại";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/product";
        }
    }

    @PostMapping("/admin/product/edit-product")
    public String updateProduct(@RequestParam("product_id") Long product_id, @RequestParam("name") String name, @RequestParam(value = "image") String image, @RequestParam("discountType") DiscountType discountType,
                              @RequestParam(value = "discountValue", defaultValue = "0") String discountValueString, @RequestParam("description") String description,
                              @RequestParam("status") boolean status, @RequestParam(value = "featured", required = false) boolean featured,
                              @RequestParam(value = "productNew", required = false) boolean productNew, @RequestParam(value = "best_seller", required = false) boolean best_seller,
                              @RequestParam("manufacturer") Manufacturer manufacturer, @RequestParam(value = "categories", required = false) Category[] categories,
                              @RequestParam(value = "imageGallery", required = false) String[] imageGallery, RedirectAttributes redirectAttributes,
                              @RequestParam(value="options.image", required = false) String[] imageOptions, @RequestParam(value="options.name", required = false) String[] nameOptions,
                              @RequestParam(value="options.price", required = false) String[] priceOptions, @RequestParam(value="options.amount", required = false) int[] amountOptions,
                                @RequestParam(value="optionsOld.image", required = false) String[] imageOptionsOld, @RequestParam(value="optionsOld.name", required = false) String[] nameOptionsOld,
                                @RequestParam(value="optionsOld.price", required = false) String[] priceOptionsOld, @RequestParam(value="optionsOld.amount", required = false) int[] amountOptionsOld,
                                @RequestParam(value="optionsOld.id", required = false) Long[] idOptionsOld) {

        try {
            Product product = productService.getOneProductById(product_id);
            product.setImage(image);
            product.setName(name);
            if (imageOptionsOld.length > 0) {
                BigDecimal price = new BigDecimal(priceOptionsOld[0]);
                product.setPrice(price);
            } else {
                if (imageOptions.length > 0) {
                    BigDecimal price = new BigDecimal(priceOptionsOld[0]);
                    product.setPrice(price);
                } else {
                    BigDecimal price = new BigDecimal(0);
                    product.setPrice(price);
                }
            }
            product.setDiscountType(discountType);
            BigDecimal discountValue = new BigDecimal(discountValueString);
            product.setDiscountValue(discountValue);
            product.setDescription(description);
            product.setStatus(status);
            product.setFeatured(featured);
            product.setProductNew(productNew);
            product.setBest_seller(best_seller);
            product.setManufacturer(manufacturer);
            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            product.setUpdatedAt(currentDateTime);

            if (categories != null && categories.length > 0) {
                product.getCategories().clear();
                product.getCategories().addAll(Arrays.asList(categories));
            }

            Product saveProduct = productService.updateProduct(product);

            if (saveProduct != null) {
                productGalleryService.deleteAllGalleryByProductId(saveProduct.getProduct_id());
                if (imageGallery != null && imageGallery.length > 0) {
                    for (String img : imageGallery) {
                        ProductGallery gallery = new ProductGallery();
                        gallery.setProduct(saveProduct);
                        gallery.setImage(img);
                        productGalleryService.saveGalleryByProductId(gallery);
                    }
                }
            }
            if (saveProduct != null) {
                if (imageOptionsOld != null && imageOptionsOld.length > 0) {
                    for (int i = 0; i < imageOptionsOld.length; i++) {
                        ProductOptions productOption = productService.getProductOptionsById(idOptionsOld[i]);
                        if (productOption != null) {
                            productOption.setProduct(saveProduct);
                            productOption.setImage(imageOptionsOld[i]);
                            productOption.setName(nameOptionsOld[i]);
                            BigDecimal priceOptionOld = new BigDecimal(priceOptionsOld[i]);
                            productOption.setPrice(priceOptionOld);
                            productOption.setAmount(amountOptionsOld[i]);
                            productService.saveProductOptions(productOption);
                        }
                    }
                }
//                productService.deleteAllProductOptionsByProductId(saveProduct.getProduct_id());
                if (imageOptions != null && imageOptions.length > 0) {
                    for (int i = 0; i < imageOptions.length; i++) {
                        ProductOptions productOptions = new ProductOptions();
                        productOptions.setProduct(saveProduct);
                        productOptions.setImage(imageOptions[i]);
                        productOptions.setName(nameOptions[i]);
                        BigDecimal priceOption = new BigDecimal(priceOptions[i]);
                        productOptions.setPrice(priceOption);
                        productOptions.setAmount(amountOptions[i]);
                        productService.saveProductOptions(productOptions);
                    }
                }
            }
            String successMessage = "Thay đổi thông tin sản phẩm thành công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/product";
        } catch (ProductException e) {
            String errorMessage = "Thay đổi thông tin sản phẩm không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/product";
        }
    }

    @GetMapping("/admin/product/delete-product")
    public String deleteProduct(@Param("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            Product product = productService.getOneProductById(id);
            if (product == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "Xóa sản phẩm không thành công");
                return "redirect:/admin/product";
            } else {
                List<ProductGallery> listImageGallery = productGalleryService.getAllGalleyByProductId(product.getProduct_id());
                if (listImageGallery != null && listImageGallery.size() > 0) {
                    productGalleryService.deleteAllGalleryByProductId(product.getProduct_id());
                }
                List<ProductOptions> listProductOptions = productService.getAllProductOptionsByProductId(product.getProduct_id());
                if (listProductOptions != null && listProductOptions.size() > 0) {
                    productService.deleteAllProductOptionsByProductId(product.getProduct_id());
                }
                productService.deleteProduct(id);
            }
            String successMessage = "Xóa sản phẩm thành công";
            redirectAttributes.addFlashAttribute("successMessage", successMessage);
            return "redirect:/admin/product";
        } catch (ProductException e) {
            String errorMessage = "Xóa sản phẩm không thành công";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/admin/product";
        }
    }
    //-----------------------------------------HOME-------------------------------------------//
    @GetMapping("/cosmetic/search")
    public String searchProductByName(Model model,  @Param("keyword") String keyword) {
        model.addAttribute("name_search", keyword);
        model.addAttribute("products", productService.searchAllProductByName(keyword));
        return "client/home/search";
    }
    @GetMapping("/cosmetic/category")
    public String getAllProductByCategoryId(Model model,  @Param("id") Long id) {
        model.addAttribute("name_category", categoryService.getOneCategory(id).getName());
        model.addAttribute("products", productService.getAllProductByCategoryId(id));
        return "client/home/all-product-by-category-id";
    }
    @GetMapping("/cosmetic/manufacturer")
    public String getAllProductByManufacturerId(Model model,  @Param("id") Long id) {
        model.addAttribute("name_manufacturer", manufacturerService.getOneManufacturer(id).getName());
        model.addAttribute("products", productService.getAllProductByManufacturerId(id));
        return "client/home/all-product-by-manufacturer-id";
    }
}
