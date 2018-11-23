package com.forum.controllers;

import com.forum.dtos.comments.CommentDto;
import com.forum.dtos.posts.CreatePostDto;
import com.forum.dtos.posts.PostDto;
import com.forum.services.CommentService;
import com.forum.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    private final CommentService commentService;

    @Autowired
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/all")
    public String allPosts(Model model) {
        List<PostDto> posts = this.postService.findAllPosts();
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
        PostDto postDto = this.postService.findById(Long.parseLong(id));
        Set<CommentDto> commentSet = this.commentService.findById(postDto);
        model.addAttribute("post", postDto);
        model.addAttribute("comments", commentSet);
        model.addAttribute("viewName", "views/posts/post-by-id");
        return "layout";
    }
}
