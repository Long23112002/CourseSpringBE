package com.example.coursel_be.service.impl;

import com.example.coursel_be.service.NotificationProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationProducerServiceImpl implements NotificationProducerService {


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendNotification(String message) {
        kafkaTemplate.send("general-notification-topic" , message);
        log.info("Notification sent : {} " , message);
    }

    @Override
    public void sendNotificationLesson(String message) {
        kafkaTemplate.send("lesson-notification-topic" , message);
        log.info("Notification Lesson sent : {} " , message);
    }
}
