package com.forum.areas.category.services;

import com.forum.areas.category.models.service.CategoryServiceModel;

import java.util.List;

public interface CategoryService {
    void create(CategoryServiceModel categoryServiceModel);

    CategoryServiceModel findByName(String name);

    List<CategoryServiceModel> findAllCategories();

    CategoryServiceModel findById(Long id);
}
