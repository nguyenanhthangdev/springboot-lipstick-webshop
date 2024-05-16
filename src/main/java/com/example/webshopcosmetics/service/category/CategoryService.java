package com.example.webshopcosmetics.service.category;

import com.example.webshopcosmetics.model.Category;
import com.example.webshopcosmetics.model.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    public List<Category> searchCategoryByName(String keyword);

    public Page<Category> getAllCategory(int pageNoString, String keyword, int size, int status);

    public List<List<Object>> getAllCategoryForEdit(Long id);

    public List<Category> getAllCategoryHierarchy();

    public String printCategoryHierarchy(Category category);

    public Category saveCategory(Category category);

    public Category updateCategory(Category category);

    public Category getOneCategory(Long id);

    public void deleteCategory(Long id);

    List<Category> getAllCategory();

//    public String printCategoryHierarchy(Category category);
}