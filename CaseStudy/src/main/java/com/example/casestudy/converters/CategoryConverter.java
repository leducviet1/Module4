package com.example.casestudy.converters;

import com.example.casestudy.model.Category;
import com.example.casestudy.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements Converter<String, Category> {
    @Autowired
    private ICategoryService categoryService;
    @Override
    public Category convert(String source) {
        Long id = Long.parseLong(source);
        Category category = categoryService.findCategoryById(id).orElse(null);
        return category;
    }
}
