package com.forum.dtos.comments;

import com.forum.areas.user.entities.User;

public class CommentDto {
    private Long id;

    private String content;

    private User user;

    public CommentDto() {
    }

    public CommentDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}