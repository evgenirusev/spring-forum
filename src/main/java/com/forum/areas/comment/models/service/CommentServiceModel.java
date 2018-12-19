package com.forum.areas.comment.models.service;

import com.forum.areas.post.models.service.PostServiceModel;
import com.forum.areas.user.models.service.UserServiceModel;

public class CommentServiceModel {
    private Long id;

    private String content;

    private PostServiceModel post;

    private UserServiceModel user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PostServiceModel getPost() {
        return post;
    }

    public void setPost(PostServiceModel post) {
        this.post = post;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }
}