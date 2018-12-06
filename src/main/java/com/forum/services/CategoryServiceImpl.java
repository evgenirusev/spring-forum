package com.forum.services;

import com.forum.dtos.category.CategoryDto;
import com.forum.dtos.category.CreateCategoryDto;
import com.forum.dtos.posts.PostDto;
import com.forum.entities.Category;
import com.forum.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public CategoryDto getCategoryByName(String name) {
        return this.categoryRepository.findByName(name);
    }

    @Override
    public List<CategoryDto> findAllCategories() {
        List<CategoryDto> categoryDtos = new ArrayList<>();

        this.categoryRepository.findAll().forEach(category -> {
            categoryDtos.add(this.modelMapper.map(category, CategoryDto.class));
        });

        return categoryDtos;
    }
}