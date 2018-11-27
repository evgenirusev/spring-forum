package com.forum.controllers;

import com.forum.common.annotations.PreAuthenticate;
import com.forum.dtos.comments.CommentDto;
import com.forum.dtos.comments.CreateCommentDto;
import com.forum.dtos.posts.CreatePostDto;
import com.forum.dtos.posts.PostDto;
import com.forum.services.CommentService;
import com.forum.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/posts")
public class PostController extends BaseController {

    private final PostService postService;

    private final CommentService commentService;

    @Autowired
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/all")
    public ModelAndView allPosts(ModelAndView modelAndView) {
        List<PostDto> posts = this.postService.findAllPosts();
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("viewName", "views/posts/all");
        return super.view("views/posts/all", "Posts", modelAndView);
    }

    @GetMapping("/create")
    @PreAuthenticate(loggedIn = true)
    public ModelAndView createPost() {
        return super.view("views/posts/create", "Create New Post");
    }

    @PostMapping("/create")
    public ModelAndView storePost(@ModelAttribute CreatePostDto createPostDto) {
        this.postService.create(createPostDto);
        return super.view("views/posts/all");
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

    @PostMapping("/{id}")
    public ModelAndView storeAnswer(@ModelAttribute CreateCommentDto createCommentDto) {
        this.commentService.save(createCommentDto);
        return super.view("views/posts/all");
    }
}
