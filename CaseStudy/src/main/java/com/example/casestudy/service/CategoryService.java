package com.example.casestudy.service;

import com.example.casestudy.model.Category;
import com.example.casestudy.repo.ICategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        if(category.getId()==null || !categoryRepository.existsById(category.getId())) {
            throw new IllegalArgumentException("Không tìm thấy sản phẩm");
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

}
