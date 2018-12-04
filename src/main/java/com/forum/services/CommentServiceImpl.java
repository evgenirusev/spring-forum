package com.forum.services;

import com.forum.dtos.comments.CommentDto;
import com.forum.dtos.comments.CreateCommentDto;
import com.forum.dtos.posts.PostDto;
import com.forum.entities.Comment;
import com.forum.entities.Post;
import com.forum.entities.User;
import com.forum.repositories.CommentRepository;
import com.forum.repositories.PostReposotory;
import com.forum.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final PostReposotory postReposotory;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostReposotory postReposotory, UserRepository userRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postReposotory = postReposotory;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<CommentDto> findById(PostDto postDto) {
        Post post = this.modelMapper.map(postDto, Post.class);
        Set<Comment> comments = this.commentRepository.findByPost(post);
        Type targetListType = new TypeToken<Set<CommentDto>>() {}.getType();
        Set<CommentDto> commentDtos = this.modelMapper.map(comments, targetListType);
        return commentDtos;
    }

    @Override
    public void save(CreateCommentDto createCommentDto, Long postId, String usernameWhichPostedTheComment) {
        Comment commentEntity = this.modelMapper.map(createCommentDto, Comment.class);
        commentEntity.setUser(this.userRepository.findOneByUsername(usernameWhichPostedTheComment));
        commentEntity.setPost(this.postReposotory.getOne(postId));
        this.commentRepository.save(commentEntity);
    }
}
