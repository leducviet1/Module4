package com.example.casestudy.service;

import com.example.casestudy.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAllCategories();
    Category createCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategoryById(Long id);
    Optional<Category> findCategoryById(Long id);
}
