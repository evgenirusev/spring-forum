package com.forum.areas.user.models.view;

import com.forum.areas.comment.models.service.CommentServiceModel;
import com.forum.areas.post.models.view.PostViewModel;

import java.util.Set;

public class UserViewModel {
    private String username;

    private String email;

    private Set<PostViewModel> posts;

    private Set<CommentServiceModel> comments;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<PostViewModel> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostViewModel> posts) {
        this.posts = posts;
    }

    public Set<CommentServiceModel> getComments() {
        return comments;
    }

    public void setComments(Set<CommentServiceModel> comments) {
        this.comments = comments;
    }
}