package com.example.coursel_be.service.impl;

import com.example.coursel_be.entity.Chapter;
import com.example.coursel_be.entity.Lesson;
import com.example.coursel_be.entity.Notification;
import com.example.coursel_be.entity.User;
import com.example.coursel_be.exceptions.AppException;
import com.example.coursel_be.exceptions.ErrorCode;
import com.example.coursel_be.repository.ChapterRepository;
import com.example.coursel_be.repository.LessonRepository;
import com.example.coursel_be.repository.NotificationRepository;
import com.example.coursel_be.repository.UserRepository;
import com.example.coursel_be.request.lesson.LessonRequest;
import com.example.coursel_be.request.lesson.LessonUpdateRequest;
import com.example.coursel_be.response.lesson.LessonResponse;
import com.example.coursel_be.service.LessonService;
import com.example.coursel_be.service.NotificationProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final ChapterRepository chapterRepository;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final NotificationProducerService notificationProducerService;

    @Override
    public String saveLesson(LessonRequest lessonRequest) {
        try {
            boolean exitsLessonTitle = lessonRepository.existsByTitle(lessonRequest.getTitle());

            if (!exitsLessonTitle) {
                Optional<Chapter> chapter = Optional.ofNullable(chapterRepository.findById(lessonRequest.getIdChapter()).orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_FOUND)));
                Optional<User> user = Optional.ofNullable(userRepository.findById(lessonRequest.getIdUserCreate()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
                Lesson lesson = getLesson(lessonRequest, chapter, user);
                lessonRepository.save(lesson);
                notificationProducerService.sendNotificationLesson("A new lesson '" + lesson.getTitle() + "' has been created.");
                return "Lesson saved successfully";
            } else {
                return "Lesson already exists";
            }
        } catch (Exception e) {
            throw new AppException(ErrorCode.UNCATEGORIZED);
        }
    }

    @Override
    public String updateLesson(LessonUpdateRequest lessonUpdateRequest) {
        try {
            Optional<Lesson> lessonOptional = lessonRepository.findById(lessonUpdateRequest.getIdLesson());
            if (lessonOptional.isPresent()) {
                Lesson lesson = lessonOptional.get();
                Optional<User> userOptional = userRepository.findById(lessonUpdateRequest.getIdUserCreate());
                Optional<Chapter> chapterOptional = chapterRepository.findById(lessonUpdateRequest.getIdChapter());
                if (userOptional.isPresent() && chapterOptional.isPresent()) {
                    if (lessonUpdateRequest.getContent() != null) {
                        lesson.setContent(lessonUpdateRequest.getContent());
                    }
                    if (lessonUpdateRequest.getLessonSequence() != null) {
                        lesson.setLessonSequence(lessonUpdateRequest.getLessonSequence());
                    }
                    if (lessonUpdateRequest.getTitle() != null) {
                        lesson.setTitle(lessonUpdateRequest.getTitle());
                    }
                    if (lessonUpdateRequest.getVideoUrl() != null) {
                        lesson.setVideoUrl(lessonUpdateRequest.getVideoUrl());
                    }
                    if (lessonUpdateRequest.getIdChapter() != null) {
                        lesson.setChapter(chapterOptional.get());
                    }
                    lesson.setUpdateBy(userOptional.get().getFullName());
                    lessonRepository.save(lesson);
                    return "Lesson updated successfully";
                }
            }
            return "Lesson updated failed";
        } catch (Exception e) {
            throw new AppException(ErrorCode.UNCATEGORIZED);
        }
    }

    @Override
    public void saveNotificationLessonForAllUsers(String message) {
        List<User> allUsers = userRepository.findAll();
        for (User u : allUsers) {
            if (u.getListRoles().stream().noneMatch(role -> role.getRoleName().equals("ROLE_ADMIN"))) {
                Notification notification = new Notification();
                notification.setMessage(message);
                notification.setUser(u);
                notificationRepository.save(notification);
            }
        }
    }

    @Override
    public LessonResponse getLessonByID(Long id) {
        try {
            Optional<Lesson> lessonOptional = lessonRepository.findById(id);
            if (lessonOptional.isPresent()) {
                LessonResponse lessonResponse = new LessonResponse();
                lessonResponse.setIdLesson(lessonOptional.get().getId());
                lessonResponse.setTitle(lessonOptional.get().getTitle());
                lessonResponse.setContent(lessonOptional.get().getContent());
                lessonResponse.setDeleted(lessonOptional.get().getDeleted());
                lessonResponse.setVideoUrl(lessonOptional.get().getVideoUrl());
                lessonResponse.setLessonSequence(lessonOptional.get().getLessonSequence());
                return lessonResponse;
            }
            return null;
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
