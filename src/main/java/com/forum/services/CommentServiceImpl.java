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

    /*
     *   Map PostDto parameter to Post for the repository query,
     *   and then map Set<Comment> to Set<CommentDto> so we don't
     *   sent @Entity objects to the web layer for correct convention.
     */
    @Override
    public Set<CommentDto> findById(PostDto postDto) {
        Post post = this.modelMapper.map(postDto, Post.class);
        Set<Comment> comments = this.commentRepository.findByPost(post);
        Type targetListType = new TypeToken<Set<CommentDto>>() {}.getType();
        Set<CommentDto> commentDtos = this.modelMapper.map(comments, targetListType);
        return commentDtos;
    }

    @Override
    public void save(CreateCommentDto createCommentDto) {
        Post post = this.postReposotory.getOne(createCommentDto.getPostId());
        User user = this.userRepository.getOne(createCommentDto.getUserId());
        Comment comment = new Comment();
        comment.setContent(createCommentDto.getContent());
        comment.setPost(post);
        comment.setUser(user);
        this.commentRepository.save(comment);
    }
}
