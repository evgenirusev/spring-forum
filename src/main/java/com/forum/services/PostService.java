package com.forum.services;

import com.forum.dtos.posts.CreatePostDto;

public interface PostService {

    void create(CreatePostDto createPostDto);

}
