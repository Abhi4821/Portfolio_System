package com.abhishekyadav.portfolio_backend;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

@RestController
public class SimpleOtpController {

    private final JavaMailSender mailSender;
    private final SecureRandom random = new SecureRandom();

    public SimpleOtpController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @GetMapping("/test/send-otp")
    public String sendOtp(@RequestParam String email) {

        String otp = String.valueOf(100000 + random.nextInt(900000));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("abhi.dev2505@gmail.com");
        message.setTo(email);
        message.setSubject("OTP Test");
        message.setText("Your OTP is: " + otp);

        mailSender.send(message);

        return "OTP sent to " + email;
    }
}
