package com.forum.areas.comment.repositories;

import com.forum.areas.comment.entities.Comment;
import com.forum.areas.post.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Set<Comment> findByPost(Post post);
}
