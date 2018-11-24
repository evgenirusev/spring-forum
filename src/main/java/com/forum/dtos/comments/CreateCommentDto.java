package com.forum.dtos.comments;

public class CreateCommentDto {

    private String content;

    private Long postId;

    public CreateCommentDto() {
    }

    public CreateCommentDto(String content, Long postId) {
        this.content = content;
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}