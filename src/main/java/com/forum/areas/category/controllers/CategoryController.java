package com.forum.areas.category.controllers;

import com.forum.areas.category.models.binding.CategoryBindingModel;
import com.forum.areas.category.models.service.CategoryServiceModel;
import com.forum.controllers.BaseController;
import com.forum.controllers.BaseControllerDeprecated;
import com.forum.areas.category.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories/create")
    public ModelAndView createCategory(@ModelAttribute CategoryBindingModel categoryBindingModel) {
        return super.view("views/categories/create", "Create Category");
    }

//    TODO: refactor
//    @PostMapping("/categories/create")
//    public ModelAndView persistCategory(@ModelAttribute CreateCategoryDto createCategoryDto) {
//        this.categoryService.save(createCategoryDto);
//        return super.view("views/categories/create");
//    }
//    @GetMapping("/categories/all")
//    public ModelAndView allCategories(ModelAndView modelAndView) {
//        List<CategoryServiceModel> categoryDtos = this.categoryService.findAllCategories();
//        modelAndView.addObject("categories", categoryDtos);
//        return super.view("views/categories/all", "Categories", modelAndView);
//    }
//
//
//
//
//    @GetMapping("/category/{categoryName}")
//    public ModelAndView findPostsByCategory(@PathVariable String categoryName) {
//        CategoryServiceModel categoryServiceModel = this.categoryService.findByName(categoryName);
//        return super.view("views/posts/all", categoryName);
//    }
}