package com.forum.services;

import com.forum.dtos.posts.CreatePostDto;
import com.forum.entities.Post;

import java.util.List;

public interface PostService {

    void create(CreatePostDto createPostDto);

    List<Post> findAllPosts();
}