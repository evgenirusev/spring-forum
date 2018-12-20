package com.forum.areas.pages.controllers;

import com.forum.abstractions.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController extends BaseController {

    @GetMapping("/")
    public ModelAndView index() {
        return super.view("views/index", "SpringAnswers");
    }

    @GetMapping("/contact")
    public ModelAndView contactPage() {
        return super.view("views/contact", "Contact");
    }

    @GetMapping("/about")
    public ModelAndView aboutPage() {
        return super.view("views/about", "About");
    }

    @GetMapping("/home")
    public ModelAndView home() {
        return super.view("views/index", "Home");
    }
}