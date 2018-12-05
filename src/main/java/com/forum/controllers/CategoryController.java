package com.forum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController extends BaseController {

    @GetMapping("categories/create")
    public ModelAndView createCategory() {
        return super.view("views/categories/create", "Create Category");
    }

}
