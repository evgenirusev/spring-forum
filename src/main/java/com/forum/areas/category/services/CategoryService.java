package com.forum.areas.category.services;

import com.forum.areas.category.models.service.CategoryServiceModel;
import com.forum.dtos.category.CategoryDto;
import com.forum.dtos.category.CategoryNamesDto;
import com.forum.dtos.category.CreateCategoryDto;

import java.util.List;

public interface CategoryService {
    void save(CreateCategoryDto createCategoryDto);

    CategoryDto getCategoryByName(String name);

    List<CategoryServiceModel> findAllCategories();

    CategoryServiceModel findById(Long id);
}
