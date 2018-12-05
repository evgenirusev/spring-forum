package com.forum.services;

import com.forum.dtos.category.CreateCategoryDto;
import com.forum.entities.Category;
import com.forum.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(CreateCategoryDto createCategoryDto) {
        Category categoryEntity = this.modelMapper.map(createCategoryDto, Category.class);
        this.categoryRepository.save(categoryEntity);
    }
}