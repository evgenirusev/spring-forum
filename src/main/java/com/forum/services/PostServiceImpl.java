package com.forum.services;

import com.forum.dtos.posts.CreatePostDto;
import com.forum.dtos.posts.PostDto;
import com.forum.entities.Post;
import com.forum.repositories.PostReposotory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return null;
    }
}
