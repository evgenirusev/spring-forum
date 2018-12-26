package com.forum.areas.user.models.view;

import com.forum.areas.comment.models.view.CommentViewModel;
import com.forum.areas.post.models.view.PostViewModel;

import java.util.Set;

public class UserProfileViewModel {

    private String username;

    private String email;

    private Set<PostViewModel> posts;

    private Set<CommentViewModel> comments;

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

    public Set<CommentViewModel> getComments() {
        return comments;
    }

    public void setComments(Set<CommentViewModel> comments) {
        this.comments = comments;
    }
}
