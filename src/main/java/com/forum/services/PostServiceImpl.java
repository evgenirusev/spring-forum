package com.forum.services;

import com.forum.dtos.posts.CreatePostDto;
import com.forum.entities.Post;
import com.forum.repositories.PostReposotory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostReposotory postReposotory;

    private final ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostReposotory postReposotory, ModelMapper modelMapper) {
        this.postReposotory = postReposotory;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(CreatePostDto createPostDto) {
        Post post = this.modelMapper.map(createPostDto, Post.class);
        this.postReposotory.save(post);
    }

    @Override
    public List<Post> findAllPosts() {
        return this.postReposotory.findAll();
    }

    @Override
    public Post findById(Long id) {
        return this.postReposotory.getOne(id);
    }
}