package com.forum.services;

import com.forum.dtos.posts.CreatePostDto;
import com.forum.dtos.posts.PostDto;
import com.forum.entities.Category;
import com.forum.entities.Post;
import com.forum.repositories.CategoryRepository;
import com.forum.repositories.PostReposotory;
import com.forum.repositories.UserRepository;
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
    public void create(CreatePostDto createPostDto) {
        Post post = new Post();
        Set<Category> categories = new HashSet<>();

        String[] categoryNames = createPostDto.getCategories().split("\\,");

        for (String categoryName : categoryNames) {
            categories.add(this.categoryRepository.findByName(categoryName));
        }
        post.setCategories(categories);
        post.setTitle(createPostDto.getTitle());
        post.setContent(createPostDto.getContent());
        post.setUser(this.userRepository.findOneByUsername(createPostDto.getUsername()));
        this.postReposotory.save(post);
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<PostDto> postDtos = new ArrayList<>();

        this.postReposotory.findAll().forEach(post -> {
            postDtos.add(this.modelMapper.map(post, PostDto.class));
        });

        return postDtos;
    }

    @Override
    public PostDto findById(Long id) {
        Post post = this.postReposotory.getOne(id);
        PostDto postDto = this.modelMapper.map(post, PostDto.class);
        return postDto;
    }
}
