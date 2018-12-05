package com.forum.controllers;

import com.forum.dtos.category.CreateCategoryDto;
import com.forum.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories/create")
    public ModelAndView createCategory(@ModelAttribute CreateCategoryDto createCategoryDto) {
        return super.view("views/categories/create", "Create Category");
    }

    @PostMapping("/categories/create")
    public ModelAndView persistCategory(@ModelAttribute CreateCategoryDto createCategoryDto) {
        this.categoryService.save(createCategoryDto);
        return super.view("views/categories/create", "Create Category");
    }

}