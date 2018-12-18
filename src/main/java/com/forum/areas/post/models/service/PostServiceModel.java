package com.forum.areas.post.models.service;

import com.forum.areas.user.models.service.UserServiceModel;

public class PostServiceModel {

    private Long id;

    private String title;

    private String content;

    private UserServiceModel user;

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
}