package com.example.coursel_be.service;

import com.example.coursel_be.entity.Course;
import com.example.coursel_be.request.CourseRequest;
import com.example.coursel_be.request.CourseUpdateRequest;
import com.example.coursel_be.response.ApiResponse;
import com.example.coursel_be.response.CourseResponse;

import java.util.List;

public interface CourseService {
   String saveCourse(CourseRequest courseRequest);
   CourseResponse getCourseById(Long courseId);
   List<CourseResponse> getAllCourses();
   String deleteCourseById(Long courseId);
   String updateCourse(CourseUpdateRequest courseUpdateRequest);
   String changeStatusCourse(Long courseId);
}
