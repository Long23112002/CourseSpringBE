package com.example.coursel_be.service.impl;

import com.example.coursel_be.entity.Chapter;
import com.example.coursel_be.entity.Course;
import com.example.coursel_be.entity.Lesson;
import com.example.coursel_be.entity.User;
import com.example.coursel_be.exceptions.AppException;
import com.example.coursel_be.exceptions.ErrorCode;
import com.example.coursel_be.repository.ChapterRepository;
import com.example.coursel_be.repository.CourseRepository;
import com.example.coursel_be.repository.LessonRepository;
import com.example.coursel_be.repository.UserRepository;
import com.example.coursel_be.request.lesson.LessonRequest;
import com.example.coursel_be.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final ChapterRepository chapterRepository;
    private final UserRepository userRepository;

    @Override
    public String saveLesson(LessonRequest lessonRequest) {
        try {
            boolean exitsLessonTitle = lessonRepository.existsByTitle(lessonRequest.getTitle());

            if (!exitsLessonTitle) {
                Optional<Chapter> chapter = Optional.ofNullable(chapterRepository.findById(lessonRequest.getIdChapter()).orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_FOUND)));
                Optional<User> user = Optional.ofNullable(userRepository.findById(lessonRequest.getIdUserCreate()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
                Lesson lesson = getLesson(lessonRequest, chapter, user);
                lessonRepository.save(lesson);
                return "Lesson saved successfully";
            }else {
                return "Lesson already exists";
            }
        } catch (Exception e) {
            throw new AppException(ErrorCode.UNCATEGORIZED);
        }
    }

    private Lesson getLesson(LessonRequest lessonRequest, Optional<Chapter> chapter, Optional<User> user) {
        if (chapter.isPresent() && user.isPresent()) {
            Lesson lesson = new Lesson();
            lesson.setLessonSequence(lessonRequest.getLessonSequence());
            lesson.setTitle(lessonRequest.getTitle());
            lesson.setChapter(chapter.get());
            lesson.setContent(lessonRequest.getContent());
            lesson.setCreateBy(user.get().getFullName());
            lesson.setUpdateBy(user.get().getFullName());
            lesson.setVideoUrl(lessonRequest.getVideoUrl());
            lesson.setDeleted(true);
            return lesson;
        } else {
            throw new AppException(ErrorCode.UNCATEGORIZED);
        }

    }
}
