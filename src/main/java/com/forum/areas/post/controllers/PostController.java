package com.forum.areas.post.controllers;

import com.forum.areas.category.models.view.CategoryViewModel;
import com.forum.areas.category.models.service.CategoryServiceModel;
import com.forum.areas.comment.models.binding.CommentBindingModel;
import com.forum.areas.comment.models.service.CommentServiceModel;
import com.forum.areas.comment.services.CommentService;
import com.forum.areas.post.models.binding.CreatePostBindingModel;
import com.forum.areas.post.models.service.PostServiceModel;
import com.forum.areas.post.models.view.PostViewModel;
import com.forum.areas.user.models.service.UserServiceModel;
import com.forum.areas.user.services.UserService;
import com.forum.abstractions.controller.BaseController;
import com.forum.areas.category.services.CategoryService;
import com.forum.areas.post.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/posts")
public class PostController extends BaseController {

    private final PostService postService;

    private final CategoryService categoryService;

    private final UserService userService;

    private final CommentService commentService;

    private final ModelMapper modelMapper;

    private List<CategoryViewModel> cacheCategoryViewModels;

    @Autowired
    public PostController(PostService postService, CategoryService categoryService, UserService userService, CommentService createCommentDto, ModelMapper modelMapper) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.commentService = createCommentDto;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public ModelAndView allPosts(@PageableDefault(size = 10) Pageable pageable) {
        Page<PostServiceModel> postServiceModels = this.postService.findAll(pageable);
        List<PostViewModel> postViewModelList = new ArrayList<>();
        postServiceModels.forEach(postServiceModel -> {
            PostViewModel postViewModel = this.modelMapper.map(postServiceModel, PostViewModel.class);
            postViewModelList.add(postViewModel);
        });
        Page<PostViewModel> postViewModelPages = new PageImpl<PostViewModel>(postViewModelList, pageable, postServiceModels .getTotalElements());
        return super.view("views/posts/all", postViewModelPages);
    }

    @GetMapping("/create")
    public ModelAndView createPost(@ModelAttribute CreatePostBindingModel createPostBindingModel) {
        List<CategoryViewModel> categoryViewModels = new ArrayList<>();
        this.categoryService.findAllCategories().forEach(categoryServiceModel -> {
            CategoryViewModel categoryViewModel =
                    this.modelMapper.map(categoryServiceModel, CategoryViewModel.class);
            categoryViewModels.add(categoryViewModel);
        });
        this.cacheCategoryViewModels = categoryViewModels;
        return super.view("views/posts/create", categoryViewModels);
    }

    @PostMapping("/create")
    public ModelAndView storePost(@Valid @ModelAttribute CreatePostBindingModel postBindingModel, BindingResult bindingResult, Authentication authentication) {

        if (bindingResult.hasErrors()) {
            return super.view("views/posts/create", this.cacheCategoryViewModels, "Create New Post");
        }
        
        PostServiceModel postServiceModel = new PostServiceModel();
        postServiceModel.setTitle(postBindingModel.getTitle());
        postServiceModel.setContent(postBindingModel.getContent());
        Set<CategoryServiceModel> categories = new HashSet<>();
        for (Long categoryId : postBindingModel.getCategories()) {
            categories.add(this.categoryService.findById(categoryId));
        }
        postServiceModel.setCategories(categories);
        UserServiceModel userServiceModel = this.userService.findByUsername(authentication.getName());
        postServiceModel.setUser(userServiceModel);
        this.postService.create(postServiceModel);
        // TODO: Implement Success Page
        return super.redirect("/");
    }

    @GetMapping("/{id}")
    public ModelAndView findPostById(@PathVariable Long id) {
        PostServiceModel postServiceModel = this.postService.findById(id);
        PostViewModel postViewModel = this.modelMapper.map(postServiceModel, PostViewModel.class);
        return super.view("views/posts/post-by-id", postViewModel);
    }

    @PostMapping("/{id}")
    public ModelAndView storeAnswer(@ModelAttribute CommentBindingModel commentBindingModel, Authentication authentication,
                                    @PathVariable(value = "id", required = true) Long postId) {
        CommentServiceModel commentServiceModel = new CommentServiceModel();
        commentServiceModel.setContent(commentBindingModel.getContent());
        PostServiceModel postServiceModel = this.postService.findById(postId);
        commentServiceModel.setPost(postServiceModel);
        UserServiceModel userServiceModel = this.userService.findByUsername(authentication.getName());
        commentServiceModel.setUser(userServiceModel);
        this.commentService.create(commentServiceModel);
        return super.redirect("/posts/" + postId);
    }
}