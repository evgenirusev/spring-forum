package com.forum.services;

import com.forum.dtos.comments.CommentDto;

import java.util.List;

public interface CommentService {
    public List<CommentDto> findById(Long id);

}