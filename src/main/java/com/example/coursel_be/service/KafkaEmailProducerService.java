package com.example.coursel_be.service;

import com.example.coursel_be.entity.Course;

public interface KafkaEmailProducerService {

    public void sendEmailNotification(String message);

}
