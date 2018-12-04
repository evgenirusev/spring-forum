package com.forum.controllers;

import com.forum.dtos.comments.CommentDto;
import com.forum.dtos.comments.CreateCommentDto;
import com.forum.dtos.posts.CreatePostDto;
import com.forum.dtos.posts.PostDto;
import com.forum.entities.Post;
import com.forum.entities.User;
import com.forum.services.CommentService;
import com.forum.services.PostService;
import com.forum.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/posts")
public class PostController extends BaseController {

    private final PostService postService;

    private final CommentService commentService;

    private final UserService userService;

    @Autowired
    public PostController(PostService postService, CommentService commentService, UserService userService) {
        this.postService = postService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public ModelAndView allPosts(ModelAndView modelAndView) {
        List<PostDto> posts = this.postService.findAllPosts();
        modelAndView.addObject("posts", posts);
        return super.view("views/posts/all", "Posts", modelAndView);
    }

    @GetMapping("/create")
    public ModelAndView createPost() {
        return super.view("views/posts/create", "Create New Post");
    }

    @PostMapping("/create")
    public ModelAndView storePost(@ModelAttribute CreatePostDto createPostDto, Authentication authentication) {
        createPostDto.setUsername(authentication.getName());
        this.postService.create(createPostDto);
        return super.view("views/posts/all");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView findPostById(@PathVariable String id, Authentication authentication, ModelAndView modelAndView) {
        PostDto postDto = this.postService.findById(Long.parseLong(id));
        Set<CommentDto> commentSet = this.commentService.findById(postDto);
        modelAndView.addObject("post", postDto);
        modelAndView.addObject("comments", commentSet);
        return super.view("views/posts/post-by-id", "Post", modelAndView);
    }

    @PostMapping("/{id}")
    public ModelAndView storeAnswer(@ModelAttribute CreateCommentDto createCommentDto,
                                    Authentication authentication,
                                    @PathVariable(value = "id", required = true) Long postId) {

        this.commentService.save(createCommentDto, postId, authentication.getName());
        return super.redirect("/posts/" + postId);
    }
}