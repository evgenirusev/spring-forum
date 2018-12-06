package com.forum.repositories;

import com.forum.dtos.category.CategoryDto;
import com.forum.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    CategoryDto findByName(String name);
}
