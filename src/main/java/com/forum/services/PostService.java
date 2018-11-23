package com.forum.services;

import com.forum.dtos.posts.CreatePostDto;
import com.forum.dtos.posts.PostDto;
import com.forum.entities.Post;

import java.util.List;

public interface PostService {

    void create(CreatePostDto createPostDto);

    List<PostDto> findAllPosts();

    PostDto findById(Long id);
}