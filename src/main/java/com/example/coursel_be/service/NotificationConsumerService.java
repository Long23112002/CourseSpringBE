package com.example.coursel_be.service;

public interface NotificationConsumerService {

    void listenNotification(String message);

    void listenNotificationLesson(String message);
}
