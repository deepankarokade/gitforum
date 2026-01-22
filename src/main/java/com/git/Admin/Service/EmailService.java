package com.git.Admin.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendProfessorCredentials(String to, String username, String password) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(to);
            message.setSubject("Professor Login Credentials");
            message.setText("Welcome to GIT Forum\n\n" +
                    "Login ID: " + username + "\n" +
                    "Password: " + password + "\n" +
                    "Please change your password after first Login\n");
            javaMailSender.send(message);
        } catch (Exception e) {
            System.err.println("Failed to send email to: " + to);
        }
    }

    public void sendStudentCredentials(String to, String username, String password) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(to);
            message.setSubject("Student Login Credentials");
            message.setText("Welcome to GIT Forum\n\n" +
                    "Login ID: " + username + "\n" +
                    "Password: " + password + "\n");
            javaMailSender.send(message);
        } catch (Exception e) {
            System.err.println("Failed to send email to: " + to);
        }
    }
}
