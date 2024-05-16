package com.example.webshopcosmetics.service.category;

import com.example.webshopcosmetics.exception.CategoryException;
import com.example.webshopcosmetics.model.Category;
import com.example.webshopcosmetics.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> searchCategoryByName(String keyword) {
        return categoryRepository.searchCategoryByName(keyword);
    }

    @Override
    public Page<Category> getAllCategory(int pageNo, String keyword, int size, int status) {
        Page<Category> pageCategory = null;
        if (keyword==null) {
            keyword = "";
        } else {
            keyword = keyword.trim();
        }
        if (categoryRepository.count() <= size) {
            pageNo = 1;
        }

        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(pageNo - 1, size, sort);
        if (status == 2) {
            // Trường hợp status=null, không lọc theo status
            pageCategory =  categoryRepository.findByNameContaining(keyword, pageable);
        } else {
            boolean boolStatus = false;
            if (status == 1) {
                boolStatus = true;
            }
            // Trường hợp status=true hoặc status=false
            pageCategory = categoryRepository.findByStatusAndNameContaining(boolStatus, keyword, pageable);
        }
//        for (int i = pageCategory.getNumberOfElements() - 1; i >=0 ; i--) {
//            String nameCategoryHierarchy = this.printCategoryHierarchy(pageCategory.getContent().get(i));
//            Category category = pageCategory.getContent().get(i);
//            category.setName(nameCategoryHierarchy);
//            pageCategory.getContent().set(i, category);
//        }
//        return pageCategory;

        List<Category> updatedCategories = new ArrayList<>();
        for (Category category : pageCategory.getContent()) {
            Category categoryTemp = new Category();
            categoryTemp.setCategory_id(category.getCategory_id());
            categoryTemp.setName(category.getName());
            categoryTemp.setImage(category.getImage());
            categoryTemp.setFeatured(category.isFeatured());
            categoryTemp.setStatus(category.isStatus());
            categoryTemp.setParent_id(category.getParent_id());
            categoryTemp.setCreatedAt(category.getCreatedAt());

            System.out.println(category.getName());
            String nameCategoryHierarchy = this.printCategoryHierarchy(categoryTemp);
            categoryTemp.setName(nameCategoryHierarchy);
            updatedCategories.add(categoryTemp);
            System.out.println(category.getName());
        }

        return new PageImpl<>(updatedCategories, pageable, pageCategory.getTotalElements());
    }

    @Override
    public String printCategoryHierarchy(Category category) {
        if (category.getParent_id() == null) {
            return category.getName();
        } else {
            return printCategoryHierarchy(category.getParent_id()) + " > " + category.getName();
        }
    }

    @Override
    public List<List<Object>> getAllCategoryForEdit(Long id) {
        Category category = categoryRepository.getOne(id);
        List<Category> categories = categoryRepository.findAll();
        // Khai báo danh sách chứa các danh sách con
        List<List<Object>> listOfLists = new ArrayList<>();
        boolean check1, check2;
        Category categoryTemp = new Category();
        for (Category cate : categories) {
            categoryTemp = cate;
            check1 = false;
            check2 = false;
            if ((category.getCategory_id().equals(categoryTemp.getCategory_id())) && (categoryTemp.getParent_id() == null)) {
                continue;
            } else if (!(category.getCategory_id().equals(categoryTemp.getCategory_id())) && (categoryTemp.getParent_id() == null)) {
                check1 = true;
            } else if ((category.getCategory_id().equals(categoryTemp.getCategory_id())) && (categoryTemp.getParent_id() != null)) {
                continue;
            }
            if (check1 == false) {
                while (true) {
                    if (category.getCategory_id().equals(categoryTemp.getCategory_id())) {
                        check2 = false;
                        break;
                    } else {
                        if (categoryTemp.getParent_id() == null) {
                            check2 = true;
                            break;
                        }
                    }
                    categoryTemp = categoryTemp.getParent_id();
                }
            } else if (check1 == true) {
                categoryTemp = cate;
                if (categoryTemp.getParent_id() == null) {
                    List<Object> list1 = new ArrayList<>();
                    list1.add(categoryTemp.getName());
                    list1.add(categoryTemp.getCategory_id());
                    listOfLists.add(list1);
                } else if (categoryTemp.getParent_id() != null) {
                    String categoryHierarchy = printCategoryHierarchy(categoryTemp);
                    List<Object> list1 = new ArrayList<>();
                    list1.add(categoryHierarchy);
                    list1.add(categoryTemp.getCategory_id());
                    listOfLists.add(list1);
                }
            }
            categoryTemp = cate;
            if ((check1 == false) && (check2 == true)) {
                String categoryHierarchy = printCategoryHierarchy(categoryTemp);
                List<Object> list1 = new ArrayList<>();
                list1.add(categoryHierarchy);
                list1.add(categoryTemp.getCategory_id());
                listOfLists.add(list1);
            }
        }
        return listOfLists;
    }

    @Override
    public List<Category> getAllCategoryHierarchy() {
        List<Category> categories = categoryRepository.findAll();
        for (int i = categories.size() - 1; i >= 0; i--) {
            String nameCategoryHierarchy = this.printCategoryHierarchy(categories.get(i));
            categories.get(i).setName(nameCategoryHierarchy);
        }
        return categories;
    }

    @Override
    public Category saveCategory(Category category) {
        try {
            return categoryRepository.save(category);
        } catch (CategoryException e) {
            throw new CategoryException("Thêm danh mục sản phẩm không thành công", e);
        }
    }

    @Override
    public Category updateCategory(Category category) {
        try {
            return categoryRepository.save(category);
        } catch (CategoryException e) {
            throw new CategoryException("Thay đổi thông tin danh mục sản phẩm không thành công", e);
        }
    }

    @Override
    public Category getOneCategory(Long id) {
        try {
            return categoryRepository.getOne(id);
        } catch (CategoryException e) {
            throw new CategoryException("Danh mục sản phẩm không tồn tại", e);
        }
    }

    @Override
    public void deleteCategory(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            // Xử lý ngoại lệ khi xóa không thành công và ném ra CategoryDeleteException
            throw new CategoryException("Xóa danh mục sản phẩm không thành công", e);
        }
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
}
