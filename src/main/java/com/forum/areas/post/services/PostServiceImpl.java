package com.forum.areas.post.services;

import com.forum.areas.post.models.service.PostServiceModel;
import com.forum.areas.post.entities.Post;
import com.forum.areas.category.repositories.CategoryRepository;
import com.forum.areas.post.repositories.PostReposotory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostReposotory postReposotory;

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostReposotory postReposotory, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.postReposotory = postReposotory;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(PostServiceModel serviceModel) {
        Post postEntity = this.modelMapper.map(serviceModel, Post.class);
        postEntity.setPublishDate(LocalDateTime.now());

        this.postReposotory.save(postEntity);
    }

    @Override
    public List<PostServiceModel> findAll() {
        List<PostServiceModel> postServiceModels = new ArrayList<>();
        this.postReposotory.findAll().forEach(post -> {
            postServiceModels.add(this.modelMapper.map(post, PostServiceModel.class));
        });
        return postServiceModels;
    }

    @Override
    public Page<PostServiceModel> findAll(Pageable pageable) {
        Page<Post> posts = this.postReposotory.findAll(pageable);
        List<PostServiceModel> postServiceModelList = new ArrayList<>();
        posts.forEach(post -> {
            PostServiceModel postServiceModel = this.modelMapper.map(post, PostServiceModel.class);
            postServiceModelList.add(postServiceModel);
        });
        Page<PostServiceModel> postServiceModels = new PageImpl<PostServiceModel>(postServiceModelList, pageable, posts.getTotalElements());
        return postServiceModels;
    }

    @Override
    public Page<PostServiceModel> findAllByName(String name, Pageable pageable) {
        Page<Post> posts = this.postReposotory.findAllByTitleContaining(name, pageable);

        List<PostServiceModel> postServiceModelList = new ArrayList<>();
        posts.forEach(post -> {
            PostServiceModel postServiceModel = this.modelMapper.map(post, PostServiceModel.class);
            postServiceModelList.add(postServiceModel);
        });
        Page<PostServiceModel> postServiceModels = new PageImpl<PostServiceModel>(postServiceModelList, pageable, posts.getTotalElements());
        return postServiceModels;
    }

    @Override
    public PostServiceModel findById(Long id) {
        Post post = this.postReposotory.findById(id).get();
        PostServiceModel postServiceModel = this.modelMapper.map(post, PostServiceModel.class);
        return postServiceModel;
    }

    @Override
    public void edit(PostServiceModel postServiceModel) {
        Post post = this.postReposotory.findById(postServiceModel.getId()).orElse(null);
        post.setTitle(postServiceModel.getTitle());
        post.setContent(postServiceModel.getContent());
        this.postReposotory.save(post);
    }

    @Override
    public void deleteById(Long id) {
        this.postReposotory.deleteById(id);
    }
}