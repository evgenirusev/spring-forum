package com.forum.repositories;

import com.forum.dtos.posts.PostDto;
import com.forum.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReposotory extends JpaRepository<Post, Long> {

}
