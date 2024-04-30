package com.example.coursel_be.repository;

import com.example.coursel_be.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog , Long> {
}
