package com.forum.controllers;

import com.forum.dtos.posts.CreatePostDto;
import com.forum.entities.Post;
import com.forum.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/all")
    public String allPosts(Model model) {
        List<Post> posts = this.postService.findAllPosts();
        model.addAttribute("posts", posts);
        model.addAttribute("viewName", "views/posts/all");
        return "layout";
    }

    @GetMapping("/create")
    public String createPost(Model model) {
        model.addAttribute("viewName", "views/posts/create");
        return "layout";
    }

    @PostMapping("/create")
    public String storePost(@ModelAttribute CreatePostDto createPostDto, Model model) {
        this.postService.create(createPostDto);
        model.addAttribute("viewName", "views/posts/all");
        return "layout";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findPostById(@PathVariable String id, Model model) {
        Post post = this.postService.findById(Long.parseLong(id));
        model.addAttribute("post", post);
        model.addAttribute("viewName", "views/posts/post-by-id");
        return "layout";
    }
}
