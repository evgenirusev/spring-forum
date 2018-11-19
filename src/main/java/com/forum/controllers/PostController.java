package com.forum.controllers;

import com.forum.dtos.posts.CreatePostDto;
import com.forum.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/create")
    public String createPost(Model model) {
        model.addAttribute("viewName", "views/posts/create");
        return "layout";
    }

    @PostMapping("/create")
    public String storePost(@ModelAttribute CreatePostDto createPostDto) {
        this.postService.create(createPostDto);
        return "test";
    }
}
