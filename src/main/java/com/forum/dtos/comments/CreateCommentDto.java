package com.forum.dtos.comments;

import com.forum.entities.Post;
import com.forum.entities.User;

public class CreateCommentDto {

    private String content;

    public CreateCommentDto() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}