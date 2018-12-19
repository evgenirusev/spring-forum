package com.forum.areas.category.models.view;

import com.forum.areas.post.models.view.PostViewModel;

import java.util.Set;

public class CategoryViewModel {

    private Long id;

    private String name;

    private String description;

    private Set<PostViewModel> posts;

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

    public Set<PostViewModel> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostViewModel> posts) {
        this.posts = posts;
    }
}