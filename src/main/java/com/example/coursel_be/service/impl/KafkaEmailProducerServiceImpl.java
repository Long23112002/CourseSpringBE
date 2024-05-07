package com.example.coursel_be.service.impl;

import com.example.coursel_be.service.KafkaEmailProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaEmailProducerServiceImpl implements KafkaEmailProducerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaEmailProducerServiceImpl.class);
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendEmailNotification(String message) {
        kafkaTemplate.send("email-notification-topic", message);
        log.info("Email notification sent: {}", message);
    }
}
