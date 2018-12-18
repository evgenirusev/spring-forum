package com.forum.areas.post.models.view;

import com.forum.areas.user.models.view.UserViewModel;

public class PostViewModel {
    private Long id;

    private String title;

    private String content;

    private UserViewModel user;

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

    public UserViewModel getUser() {
        return user;
    }

    public void setUser(UserViewModel user) {
        this.user = user;
    }
}