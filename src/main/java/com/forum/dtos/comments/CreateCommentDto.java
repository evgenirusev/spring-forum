package com.forum.dtos.comments;

public class CreateCommentDto {

    private String content;

    public CreateCommentDto() {
    }

    public CreateCommentDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}