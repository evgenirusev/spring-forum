package com.forum.dtos.posts;

public class CreatePostDto {

    private Long userId;

    private String title;

    private String content;

    private String categories;

    public CreatePostDto() {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String category) {
        this.categories = category;
    }
}