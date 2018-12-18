package com.forum.areas.post.controllers;

import com.forum.areas.post.models.service.PostServiceModel;
import com.forum.areas.post.models.view.PostViewModel;
import com.forum.controllers.BaseController;
import com.forum.dtos.category.CategoryNamesDto;
import com.forum.dtos.comments.CommentDto;
import com.forum.dtos.comments.CreateCommentDto;
import com.forum.dtos.posts.CreatePostDto;
import com.forum.dtos.posts.PostDto;
import com.forum.areas.category.services.CategoryService;
import com.forum.areas.comment.services.CommentService;
import com.forum.areas.post.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/posts")
public class PostController extends BaseController {

    private final PostService postService;

    private final CommentService commentService;

    private final CategoryService categoryService;

    private final ModelMapper modelMapper;

    @Autowired
    public PostController(PostService postService, CommentService commentService, CategoryService categoryService, ModelMapper modelMapper) {
        this.postService = postService;
        this.commentService = commentService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public ModelAndView allPosts(ModelAndView modelAndView) {
        List<PostViewModel> postViewModels = new ArrayList<>();
        this.postService.findAllPosts().forEach(postServiceModel -> {
            PostViewModel postViewModel = this.modelMapper.map(postServiceModel, PostViewModel.class);
            postViewModels.add(postViewModel);
        });
        return super.view("views/posts/all", postViewModels);
    }

    @GetMapping("/create")
    public ModelAndView createPost(@ModelAttribute CreatePostDto createPostDto, ModelAndView modelAndView) {
        List<CategoryNamesDto> categoryNamesDtos = this.categoryService.findAllCategoryFormDtos();
        modelAndView.addObject("categories", categoryNamesDtos);
        modelAndView.addObject("createPostDto", createPostDto);
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
        return super.view("views/posts/post-by-id", "Post");
    }

    @PostMapping("/{id}")
    public ModelAndView storeAnswer(@ModelAttribute CreateCommentDto createCommentDto,
                                    Authentication authentication,
                                    @PathVariable(value = "id", required = true) Long postId) {
        this.commentService.save(createCommentDto, postId, authentication.getName());
        return super.redirect("/posts/" + postId);
    }

    @GetMapping("/category/{categoryName}")
    public ModelAndView findPostsByCategory(@PathVariable String categoryName, ModelAndView modelAndView) {
        List<PostDto> postDtos = this.postService.findByCategory(categoryName);
        modelAndView.addObject("posts", postDtos);
        modelAndView.addObject("postsCategoryName", categoryName);
        return super.view("views/posts/all", categoryName);
    }
}