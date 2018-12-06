package com.forum.services;

import com.forum.dtos.category.CategoryDto;
import com.forum.dtos.category.CreateCategoryDto;

import java.util.List;

public interface CategoryService {
    void save(CreateCategoryDto createCategoryDto);

    CategoryDto getCategoryByName(String name);

    List<CategoryDto> findAllCategories();
}
