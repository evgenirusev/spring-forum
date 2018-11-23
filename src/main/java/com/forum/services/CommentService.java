package com.forum.services;

import com.forum.dtos.comments.CommentDto;
import com.forum.dtos.posts.PostDto;

import java.util.Set;

public interface CommentService {
    public Set<CommentDto> findById(PostDto postDto);
}