package com.example.coursel_be.service.impl;

import com.example.coursel_be.service.EmailProducerService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailProducerServiceImpl implements EmailProducerService {

    private static final Logger log = LoggerFactory.getLogger(EmailProducerServiceImpl.class);
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendEmailNotification(String message) {
        kafkaTemplate.send("email-notifications-topic", message);
        log.info("Email notification sent: {}", message);
    }
}
