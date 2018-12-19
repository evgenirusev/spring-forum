package com.forum.areas.category.services;

import com.forum.areas.category.models.service.CategoryServiceModel;

import java.util.List;

public interface CategoryService {
//    TODO: refactor obsolete code
//    void save(CreateCategoryDto createCategoryDto);

    CategoryServiceModel findByName(String name);

    List<CategoryServiceModel> findAllCategories();

    CategoryServiceModel findById(Long id);
}
