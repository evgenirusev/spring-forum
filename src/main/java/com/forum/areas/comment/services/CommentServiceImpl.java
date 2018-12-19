package com.forum.areas.comment.services;

import com.forum.areas.comment.models.service.CommentServiceModel;
import com.forum.areas.comment.entities.Comment;
import com.forum.areas.comment.repositories.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(CommentServiceModel commentServiceModel) {
        Comment commentEntity = this.modelMapper.map(commentServiceModel, Comment.class);
        this.commentRepository.save(commentEntity);
    }
}
