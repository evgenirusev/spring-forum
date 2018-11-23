package com.forum.dtos.comments;

import com.forum.dtos.posts.PostDto;
import com.forum.entities.Post;

public class CreateCommentDto {

    private String content;

//    TODO: DETERMINE DTO OR POST
//    private PostDto post;

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