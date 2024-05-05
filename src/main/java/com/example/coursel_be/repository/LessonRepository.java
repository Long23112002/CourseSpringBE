package com.example.coursel_be.repository;


import com.example.coursel_be.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    boolean existsByTitle(String title);

}
