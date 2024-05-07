package com.example.coursel_be.service.impl;

import com.example.coursel_be.entity.User;
import com.example.coursel_be.repository.UserRepository;
import com.example.coursel_be.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;


    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void sendMessage(String from, String to, String subject, String message) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(message, true);
            emailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmailToAllUsers(String message) {
        String messageBody = "<html><body>" +
                "<h2>New Course Created</h2>" +
                "<p>A new course has been created:</p>" +
                "<p><strong>Course Title:</strong> " + message  + "</p>" +
                "<img src=\"https://img-angularjs.s3.amazonaws.com/productimage/avatar.jpg\" alt=\"New Course\" style=\"width: 300px; border-radius: 5%;\">" +
                "<p>For more details, please visit our website.</p>" +
                "</body></html>";
        List<User> allUsers = userRepository.findAll();

        for (User user : allUsers) {
            sendMessage("longjava2024@gmail.com",user.getEmail(), "Subject of Email", messageBody);
        }
    }


}
