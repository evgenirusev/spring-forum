package com.forum.areas.post.services;

import com.forum.dtos.posts.CreatePostDto;
import com.forum.dtos.posts.PostDto;

import java.util.List;

public interface PostService {

    void create(CreatePostDto createPostDto);

    List<PostDto> findAllPosts();

    PostDto findById(Long id);

    List<PostDto> findByCategory(String categoryName);
}