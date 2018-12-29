package com.forum.areas.comment.models.binding;

import com.forum.constants.Constants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CommentBindingModel {
    @Size(min = 3, max = 1000, message = Constants.COMMENT_LENGTH)
    private String commentContent;

    public CommentBindingModel() {
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
