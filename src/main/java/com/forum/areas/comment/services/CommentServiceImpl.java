package com.forum.areas.comment.services;

import com.forum.areas.comment.models.service.CommentServiceModel;
import com.forum.dtos.comments.CommentDto;
import com.forum.dtos.comments.CreateCommentDto;
import com.forum.dtos.posts.PostDto;
import com.forum.areas.comment.entities.Comment;
import com.forum.areas.post.entities.Post;
import com.forum.areas.comment.repositories.CommentRepository;
import com.forum.areas.post.repositories.PostReposotory;
import com.forum.areas.user.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Set;

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
