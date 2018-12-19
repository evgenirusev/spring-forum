package com.forum.areas.post.models.view;

import com.forum.areas.category.models.view.CategoryViewModel;
import com.forum.areas.comment.models.view.CommentViewModel;
import com.forum.areas.user.models.view.UserViewModel;

import java.util.Set;

public class PostViewModel {
    private Long id;

    private String title;

    private String content;

    private UserViewModel user;

    private Set<CategoryViewModel> categories;

    private Set<CommentViewModel> comments;

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

    public Set<CategoryViewModel> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryViewModel> categories) {
        this.categories = categories;
    }

    public Set<CommentViewModel> getComments() {
        return comments;
    }

    public void setComments(Set<CommentViewModel> comments) {
        this.comments = comments;
    }
}