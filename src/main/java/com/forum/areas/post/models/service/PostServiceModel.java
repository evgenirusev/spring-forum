package com.forum.areas.post.models.service;

import com.forum.areas.category.models.service.CategoryServiceModel;
import com.forum.areas.comment.models.service.CommentServiceModel;
import com.forum.areas.user.models.service.UserServiceModel;

import java.util.Set;

public class PostServiceModel {

    private Long id;

    private String title;

    private String content;

    private UserServiceModel user;

    private Set<CategoryServiceModel> categories;

    private Set<CommentServiceModel> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }

    public Set<CategoryServiceModel> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryServiceModel> categories) {
        this.categories = categories;
    }

    public Set<CommentServiceModel> getComments() {
        return comments;
    }

    public void setComments(Set<CommentServiceModel> comments) {
        this.comments = comments;
    }
}