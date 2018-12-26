package com.forum.areas.post.services;

import com.forum.areas.post.models.service.PostServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    void create(PostServiceModel postServiceModel);

    List<PostServiceModel> findAll();

    Page<PostServiceModel> findAll(Pageable pageable);

    Page<PostServiceModel> findAllByName(String name, Pageable pageable);

    PostServiceModel findById(Long id);
}