package com.example.coursel_be.service;

import com.example.coursel_be.request.lesson.LessonRequest;

public interface LessonService {

    String saveLesson(LessonRequest lessonRequest);
}
