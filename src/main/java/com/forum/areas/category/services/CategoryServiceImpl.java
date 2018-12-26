package com.forum.areas.category.services;

import com.forum.areas.category.models.service.CategoryServiceModel;
import com.forum.areas.category.entities.Category;
import com.forum.areas.category.repositories.CategoryRepository;
import com.forum.areas.post.models.service.PostServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public void create(CategoryServiceModel categoryServiceModel) {
        Category category = this.modelMapper.map(categoryServiceModel, Category.class);
        this.categoryRepository.save(category);
    }

    @Override
    public CategoryServiceModel findByName(String name) {
        Category category = this.categoryRepository.findByName(name);
        CategoryServiceModel categoryServiceModel = this.modelMapper.map(category, CategoryServiceModel.class);
        return categoryServiceModel;
    }

    @Override
    public Set<CategoryServiceModel> findAll() {
        Set<CategoryServiceModel> categoryServiceModels = new HashSet<>();
        this.categoryRepository.findAll().forEach(category -> {
            CategoryServiceModel serviceModel = this.modelMapper.map(category, CategoryServiceModel.class);
            categoryServiceModels.add(serviceModel);
        });
        return categoryServiceModels;
    }

    @Override
    public Set<String> findAllNames() {
        return this.categoryRepository.findAllCategoryNames();
    }

    @Override
    public CategoryServiceModel findById(Long id) {
        Category categoryEntity = this.categoryRepository.findById(id).orElse(null);
        return this.modelMapper.map(categoryEntity, CategoryServiceModel.class);
    }
}