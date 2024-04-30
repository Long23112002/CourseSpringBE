package com.example.coursel_be.repository;

import com.example.coursel_be.entity.Lessons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lessons, Long> {
}
