package com.example.coursel_be.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface EmailService {

    void sendMessage(String from,String to, String subject, String message);

    void sendEmailToAllUsers(String message) throws JsonProcessingException;
}
