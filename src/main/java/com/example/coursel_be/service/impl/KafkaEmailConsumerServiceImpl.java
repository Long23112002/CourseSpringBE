package com.example.coursel_be.service.impl;

import com.example.coursel_be.service.EmailService;
import com.example.coursel_be.service.KafkaEmailConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaEmailConsumerServiceImpl implements KafkaEmailConsumerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaEmailConsumerServiceImpl.class);
    @Autowired
    private EmailService emailService;



    @Override
    @KafkaListener(topics = "email-notification-topic" , groupId = "email-group")
    public void listenEmailNotifications(String message) {
        emailService.sendEmailToAllUsers(message);
        log.info("Email sent to all users");
    }

}
