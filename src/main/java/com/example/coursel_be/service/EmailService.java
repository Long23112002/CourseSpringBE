package com.example.coursel_be.service;

public interface EmailService {

    void sendMessage(String from,String to, String subject, String message);

    void sendEmailToAllUsers(String message);
}
