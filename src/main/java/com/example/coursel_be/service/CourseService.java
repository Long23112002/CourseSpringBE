package com.example.coursel_be.service;

import com.example.coursel_be.request.course.CourseRequest;
import com.example.coursel_be.request.course.CourseUpdateRequest;
import com.example.coursel_be.response.course.CourseResponse;

import java.util.List;

public interface CourseService {

    String saveCourse(CourseRequest courseRequest);

    CourseResponse getCourseById(Long courseId);

    List<CourseResponse> getAllCourses();

    String deleteCourseById(Long courseId);

    String updateCourse(CourseUpdateRequest courseUpdateRequest);

    String changeStatusCourse(Long courseId);

    void saveNotificationForAllUsers(String message);

}
