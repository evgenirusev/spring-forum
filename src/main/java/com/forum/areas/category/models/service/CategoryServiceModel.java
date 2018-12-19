package com.forum.areas.category.models.service;

import com.forum.areas.post.models.service.PostServiceModel;

import java.util.Set;

public class CategoryServiceModel {
    private Long id;

    private String name;

    private String description;

    private Set<PostServiceModel> posts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<PostServiceModel> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostServiceModel> posts) {
        this.posts = posts;
    }
}