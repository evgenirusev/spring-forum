package com.forum.areas.comment.models.view;

import com.forum.areas.post.models.view.PostViewModel;
import com.forum.areas.user.models.view.UserViewModel;

import java.time.LocalDateTime;

public class CommentViewModel {

    private Long id;

    private String content;

    private LocalDateTime publishDate;

    private PostViewModel post;

    private UserViewModel user;

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

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public PostViewModel getPost() {
        return post;
    }

    public void setPost(PostViewModel post) {
        this.post = post;
    }

    public UserViewModel getUser() {
        return user;
    }

    public void setUser(UserViewModel user) {
        this.user = user;
    }
}
