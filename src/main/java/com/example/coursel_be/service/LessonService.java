package com.example.coursel_be.service;

import com.example.coursel_be.request.lesson.LessonRequest;
import com.example.coursel_be.request.lesson.LessonUpdateRequest;
import com.example.coursel_be.response.lesson.LessonResponse;

import java.util.List;

public interface LessonService {

    String saveLesson(LessonRequest lessonRequest);

    String updateLesson(LessonUpdateRequest lessonUpdateRequest);

    void  saveNotificationLessonForAllUsers(String message);

    LessonResponse getLessonByID(Long id);
}
