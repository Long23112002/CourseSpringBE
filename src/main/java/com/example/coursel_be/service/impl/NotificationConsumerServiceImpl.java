package com.example.coursel_be.service.impl;

import com.example.coursel_be.service.CourseService;
import com.example.coursel_be.service.LessonService;
import com.example.coursel_be.service.NotificationConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationConsumerServiceImpl implements NotificationConsumerService {

    private CourseService courseService;
    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    private LessonService lessonService;
    @Autowired
    public void setLessonService(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @Override
    @KafkaListener(topics = "general-notification-topic" , groupId = "notification-group")
    public void listenNotification(String message) {
        courseService.saveNotificationForAllUsers(message);
        log.info("Send all notification user");
    }

    @Override
    @KafkaListener(topics = "lesson-notification-topic" , groupId = "notification-group")
    public void listenNotificationLesson(String message) {
       lessonService.saveNotificationLessonForAllUsers(message);
         log.info("Send all notification lesson");
    }
}
