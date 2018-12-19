package com.forum.areas.post.services;

import com.forum.areas.post.models.service.PostServiceModel;
import com.forum.dtos.posts.CreatePostDto;
import com.forum.dtos.posts.PostDto;
import com.forum.areas.category.entities.Category;
import com.forum.areas.post.entities.Post;
import com.forum.areas.category.repositories.CategoryRepository;
import com.forum.areas.post.repositories.PostReposotory;
import com.forum.areas.user.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {

    private final PostReposotory postReposotory;

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostReposotory postReposotory, UserRepository userRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.postReposotory = postReposotory;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(PostServiceModel serviceModel) {
        Post postEntity = this.modelMapper.map(serviceModel, Post.class);
        this.postReposotory.save(postEntity);
    }

    @Override
    public List<PostServiceModel> findAllPosts() {
        List<PostServiceModel> postServiceModels = new ArrayList<>();
        this.postReposotory.findAll().forEach(post -> {
            postServiceModels.add(this.modelMapper.map(post, PostServiceModel.class));
        });
        return postServiceModels;
    }

    @Override
    public PostDto findById(Long id) {
        Post post = this.postReposotory.getOne(id);
        PostDto postDto = this.modelMapper.map(post, PostDto.class);
        return postDto;
    }

    @Override
    public List<PostDto> findByCategory(String categoryName) {
        List<PostDto> postDtos = new ArrayList<>();

        Category category = this.categoryRepository.findByName(categoryName);

        List<Post> postList = this.postReposotory.findAllByCategoriesContaining(category);

        postList.forEach(post -> {
            postDtos.add(this.modelMapper.map(post, PostDto.class));
        });

        return postDtos;
    }
}