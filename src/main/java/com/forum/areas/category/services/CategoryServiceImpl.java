package com.forum.areas.category.services;

import com.forum.areas.category.models.service.CategoryServiceModel;
import com.forum.areas.category.entities.Category;
import com.forum.areas.category.repositories.CategoryRepository;
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
    public CategoryServiceModel findByName(String name) {
        Category category = this.categoryRepository.findByName(name);
        CategoryServiceModel categoryServiceModel = this.modelMapper.map(category, CategoryServiceModel.class);
        return categoryServiceModel;
    }

    @Override
    public List<CategoryServiceModel> findAllCategories() {
        List<CategoryServiceModel> categoryServiceModels = new ArrayList<>();
        this.categoryRepository.findAll().forEach(category -> {
            CategoryServiceModel serviceModel = this.modelMapper.map(category, CategoryServiceModel.class);
            categoryServiceModels.add(serviceModel);
        });
        return categoryServiceModels;
    }

    @Override
    public CategoryServiceModel findById(Long id) {
        Category categoryEntity = this.categoryRepository.findById(id).orElse(null);
        return this.modelMapper.map(categoryEntity, CategoryServiceModel.class);
    }
}