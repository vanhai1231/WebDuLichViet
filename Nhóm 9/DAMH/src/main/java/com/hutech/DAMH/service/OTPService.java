package com.hutech.DAMH.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
public class OTPService {

    @Autowired
    private JavaMailSender mailSender;

    private final Random random = new Random();

    public String generateOTP() {
        return String.format("%06d", random.nextInt(999999));
    }

    public void sendOTPEmail(String email, String otp) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject("Your OTP Code");
//        message.setText("Your OTP code is: " + otp);
//
//        mailSender.send(message);
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject("Your OTP Code");
            helper.setText("Your OTP code is: " + otp, true);
            helper.setFrom("kikyoutnt33@gmail.com", "Du Lịch Việt");
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    public String generateRandomPassword() {
        // Generate a random password
        int length = 10;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }

    public void sendNewPasswordEmail(String email, String newPassword, String resetLink) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject("Your New Password");
            helper.setText("Your new password is: " + newPassword + "<br>Click <a href=\"" + resetLink + "\">here</a> to change your password.", true);
            helper.setFrom("kikyoutnt33@gmail.com", "Du Lịch Việt");
            mailSender.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }
}
