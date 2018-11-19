package com.forum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("posts")
public class PostController {

    @GetMapping("/create")
    public String createPost(Model model) {
        model.addAttribute("viewName", "views/posts/create");
        return "layout";
    }

}
