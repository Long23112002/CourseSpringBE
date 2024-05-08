package com.example.coursel_be.service;

public interface NotificationProducerService {

    void sendNotification(String message);

    void sendNotificationLesson(String message);
}
