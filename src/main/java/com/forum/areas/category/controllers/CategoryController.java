package com.forum.areas.category.controllers;

import com.forum.areas.category.models.binding.CategoryBindingModel;
import com.forum.areas.category.models.service.CategoryServiceModel;
import com.forum.controllers.BaseController;
import com.forum.areas.category.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/categories/create")
    public ModelAndView createCategory(@ModelAttribute CategoryBindingModel categoryBindingModel) {
        return super.view("/views/categories/create", "Create Category");
    }

    @PostMapping("/categories/create")
    public ModelAndView persistCategory(@Valid @ModelAttribute CategoryBindingModel categoryBindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return super.view("/views/categories/create", "Create Category");
        }
        CategoryServiceModel categoryServiceModel = this.modelMapper.map(categoryBindingModel, CategoryServiceModel.class);
        this.categoryService.create(categoryServiceModel);
        return super.redirect("/categories/all");
    }

//    TODO: refactor
//    @GetMapping("/categories/all")
//    public ModelAndView allCategories(ModelAndView modelAndView) {
//        List<CategoryServiceModel> categoryDtos = this.categoryService.findAllCategories();
//        modelAndView.addObject("categories", categoryDtos);
//        return super.view("views/categories/all", "Categories", modelAndView);
//    }
//    @GetMapping("/category/{categoryName}")
//    public ModelAndView findPostsByCategory(@PathVariable String categoryName) {
//        CategoryServiceModel categoryServiceModel = this.categoryService.findByName(categoryName);
//        return super.view("views/posts/all", categoryName);
//    }
}