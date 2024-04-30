package com.example.coursel_be.repository;

import com.example.coursel_be.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    public boolean existsByTitle(String courseName);

    public boolean existsById(Long courseId);
}
