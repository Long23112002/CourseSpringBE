package com.example.coursel_be.repository;

import com.example.coursel_be.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
     boolean existsByTitle(String courseName);

     boolean existsById(long courseId);

}
