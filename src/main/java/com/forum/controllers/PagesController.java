package com.forum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PagesController extends BaseController {

    @RequestMapping(value = {"", "/", "/home"})
    public ModelAndView index() {
        return super.view("views/index", "Home");
    }

    @GetMapping("/contact")
    public ModelAndView contactPage() {
        return super.view("views/contact", "Contact");
    }

    @GetMapping("/about")
    public ModelAndView aboutPage() {
        return super.view("views/about", "About");
    }
}