package com.forum.areas.post.controllers;

import com.forum.areas.category.models.view.CategoryViewModel;
import com.forum.areas.category.models.service.CategoryServiceModel;
import com.forum.areas.comment.models.binding.CommentBindingModel;
import com.forum.areas.comment.models.service.CommentServiceModel;
import com.forum.areas.comment.services.CommentService;
import com.forum.areas.post.models.binding.CreatePostBindingModel;
import com.forum.areas.post.models.binding.EditPostBindingModel;
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

    private PostViewModel postViewModelCache;

    @Autowired
    public PostController(PostService postService, CategoryService categoryService, UserService userService, CommentService createCommentDto, ModelMapper modelMapper) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.commentService = createCommentDto;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public ModelAndView allPosts(@PageableDefault(size = 10) Pageable pageable, @RequestParam(value = "search", required = false) String searchGetParameter) {
        Page<PostServiceModel> postServiceModels;
        if (searchGetParameter != null) {
            postServiceModels = this.postService.findAllByName(searchGetParameter, pageable);
        } else {
            postServiceModels = this.postService.findAll(pageable);
        }

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
        this.categoryService.findAll().forEach(categoryServiceModel -> {
            CategoryViewModel categoryViewModel =
                    this.modelMapper.map(categoryServiceModel, CategoryViewModel.class);
            categoryViewModels.add(categoryViewModel);
        });
        this.cacheCategoryViewModels = categoryViewModels;
        return super.view("views/posts/create", categoryViewModels);
    }

    @PostMapping("/create")
    public ModelAndView storePost(@Valid @ModelAttribute CreatePostBindingModel createPostBindingModel, BindingResult bindingResult, Authentication authentication) {

        if (bindingResult.hasErrors()) {
            return super.view("views/posts/create", this.cacheCategoryViewModels, "Create New Post");
        }
        
        PostServiceModel postServiceModel = this.modelMapper.map(createPostBindingModel, PostServiceModel.class);
        Set<CategoryServiceModel> categories = new TreeSet<CategoryServiceModel>(Comparator.comparing(CategoryServiceModel::getId));

        for (Long categoryId : createPostBindingModel.getCategories()) {
            categories.add(this.categoryService.findById(categoryId));
        }

        postServiceModel.setCategories(categories);
        UserServiceModel userServiceModel = this.userService.findByUsername(authentication.getName());
        postServiceModel.setUser(userServiceModel);
        this.postService.create(postServiceModel);
        return super.redirect("/posts");
    }

    @GetMapping("/{id}")
    public ModelAndView findPostById(@PathVariable Long id, @ModelAttribute CommentBindingModel commentBindingModel) {
        PostServiceModel postServiceModel = this.postService.findById(id);
        PostViewModel postViewModel = this.modelMapper.map(postServiceModel, PostViewModel.class);
        this.postViewModelCache =  postViewModel;
        return super.view("views/posts/post-by-id", postViewModel);
    }

    @PostMapping("/{id}")
    public ModelAndView storeAnswer(@Valid @ModelAttribute CommentBindingModel commentBindingModel, BindingResult bindingResult, Authentication authentication,
                                    @PathVariable(value = "id", required = true) Long postId) {

        if (bindingResult.hasErrors()) {
            return super.view("views/posts/post-by-id", this.postViewModelCache);
        }

        CommentServiceModel commentServiceModel = new CommentServiceModel();
        commentServiceModel.setContent(commentBindingModel.getCommentContent());
        PostServiceModel postServiceModel = this.postService.findById(postId);
        commentServiceModel.setPost(postServiceModel);
        UserServiceModel userServiceModel = this.userService.findByUsername(authentication.getName());
        commentServiceModel.setUser(userServiceModel);
        this.commentService.create(commentServiceModel);
        this.postViewModelCache = null;
        return super.redirect("/posts/" + postId);
    }

    @PostMapping("/{id}/edit")
    public ModelAndView editConfirm(@Valid @ModelAttribute EditPostBindingModel editPostBindingModel, BindingResult bindingResult, @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            return super.redirect("/posts/" + id);
        }
        PostServiceModel postServiceModel = this.modelMapper.map(editPostBindingModel, PostServiceModel.class);
        this.postService.edit(postServiceModel);
        return redirect("/posts/" + id);
    }

    @PostMapping("/{id}/delete")
    public ModelAndView deleteConfirm(@PathVariable Long id) {
        this.postService.deleteById(id);
        return redirect("/posts");
    }
}