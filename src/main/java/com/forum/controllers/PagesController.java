package com.forum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {

    @RequestMapping(value = {"", "/", "/home"})
    public String index(Model model) {
        model.addAttribute("viewName", "views/index");
        return "layout";
    }

    @GetMapping("/contact")
    public String contactPage(Model model) {
        model.addAttribute("viewName", "views/contact");
        return "layout";
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("viewName", "views/about");
        return "layout";
    }
}