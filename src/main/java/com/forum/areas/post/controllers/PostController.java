package com.forum.areas.post.controllers;

import com.forum.areas.category.models.binding.CategoryCreatePostViewModel;
import com.forum.areas.category.models.service.CategoryServiceModel;
import com.forum.areas.post.models.binding.CreatePostBindingModel;
import com.forum.areas.post.models.service.PostServiceModel;
import com.forum.areas.post.models.view.PostViewModel;
import com.forum.areas.user.models.service.UserServiceModel;
import com.forum.areas.user.services.UserService;
import com.forum.controllers.BaseController;
import com.forum.areas.category.services.CategoryService;
import com.forum.areas.post.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final ModelMapper modelMapper;

    private List<CategoryCreatePostViewModel> cacheCategoryViewModels;

    @Autowired
    public PostController(PostService postService, CategoryService categoryService, UserService userService, ModelMapper modelMapper) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public ModelAndView allPosts() {
        List<PostViewModel> postViewModels = new ArrayList<>();
        this.postService.findAllPosts().forEach(postServiceModel -> {
            PostViewModel postViewModel = this.modelMapper.map(postServiceModel, PostViewModel.class);
            postViewModels.add(postViewModel);
        });
        return super.view("views/posts/all", postViewModels);
    }

    @GetMapping("/create")
    public ModelAndView createPost(@ModelAttribute CreatePostBindingModel createPostBindingModel) {
        List<CategoryCreatePostViewModel> categoryViewModels = new ArrayList<>();
        this.categoryService.findAllCategories().forEach(categoryServiceModel -> {
            CategoryCreatePostViewModel categoryViewModel =
                    this.modelMapper.map(categoryServiceModel, CategoryCreatePostViewModel.class);
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
        return null;
    }

//     TODO: Refactor
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public ModelAndView findPostById(@PathVariable String id, Authentication authentication, ModelAndView modelAndView) {
//        PostDto postDto = this.postService.findById(Long.parseLong(id));
//        Set<CommentDto> commentSet = this.commentService.findById(postDto);
//        modelAndView.addObject("post", postDto);
//        modelAndView.addObject("comments", commentSet);
//        return super.view("views/posts/post-by-id", "Post");
//    }
//
//    @PostMapping("/{id}")
//    public ModelAndView storeAnswer(@ModelAttribute CreateCommentDto createCommentDto,
//                                    Authentication authentication,
//                                    @PathVariable(value = "id", required = true) Long postId) {
//
//        this.commentService.save(createCommentDto, postId, authentication.getName());
//        return super.redirect("/posts/" + postId);
//    }
//
//    @GetMapping("/category/{categoryName}")
//    public ModelAndView findPostsByCategory(@PathVariable String categoryName, ModelAndView modelAndView) {
//        List<PostDto> postDtos = this.postService.findByCategory(categoryName);
//        modelAndView.addObject("posts", postDtos);
//        modelAndView.addObject("postsCategoryName", categoryName);
//        return super.view("views/posts/all", categoryName);
//    }
}