package com.forum.areas.category.services;

import com.forum.areas.category.models.service.CategoryServiceModel;

import java.util.Set;

public interface CategoryService {
    void create(CategoryServiceModel categoryServiceModel);

    CategoryServiceModel findByName(String name);

    Set<CategoryServiceModel> findAll();

    Set<String> findAllNames();

    CategoryServiceModel findById(Long id);
}
