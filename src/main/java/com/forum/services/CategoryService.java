package com.forum.services;

import com.forum.dtos.category.CreateCategoryDto;

public interface CategoryService {
    void save(CreateCategoryDto createCategoryDto);
}
