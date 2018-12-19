package com.forum.areas.comment.services;

import com.forum.areas.comment.models.service.CommentServiceModel;
import com.forum.dtos.comments.CommentDto;
import com.forum.dtos.comments.CreateCommentDto;
import com.forum.dtos.posts.PostDto;

import java.util.Set;

public interface CommentService {
    public void create(CommentServiceModel commentServiceModel);
}