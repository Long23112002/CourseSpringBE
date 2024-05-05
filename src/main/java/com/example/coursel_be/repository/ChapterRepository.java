package com.example.coursel_be.repository;

import com.example.coursel_be.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    boolean existsByTitle(String title);

    List<Chapter>findAllByCourseId(Long courseId);
}
