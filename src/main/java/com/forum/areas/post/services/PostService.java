package com.forum.areas.post.services;

import com.forum.areas.post.models.service.PostServiceModel;
import com.forum.dtos.posts.PostDto;

import java.util.List;

public interface PostService {

    void create(PostServiceModel postServiceModel);

    List<PostServiceModel> findAllPosts();

    PostServiceModel findById(Long id);

    List<PostDto> findByCategory(String categoryName);
}