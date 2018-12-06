package com.forum.repositories;

import com.forum.entities.Category;
import com.forum.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostReposotory extends JpaRepository<Post, Long> {
    List<Post> findAllByCategoriesContaining(Category categories);
}
