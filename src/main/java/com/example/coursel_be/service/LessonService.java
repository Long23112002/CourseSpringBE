package com.example.coursel_be.service;

import com.example.coursel_be.request.lesson.LessonRequest;
import com.example.coursel_be.request.lesson.LessonUpdateRequest;

public interface LessonService {

    String saveLesson(LessonRequest lessonRequest);

    String updateLesson(LessonUpdateRequest lessonUpdateRequest);
}
