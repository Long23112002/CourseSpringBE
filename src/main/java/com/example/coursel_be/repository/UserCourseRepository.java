package com.example.coursel_be.repository;

import com.example.coursel_be.entity.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
    @Query("SELECT COUNT(uc) > 0 FROM UserCourse uc WHERE uc.user.id = :userId AND uc.course.id = :courseId")
    boolean existsByUserIdAndCourseId(Long userId, Long courseId);
}
