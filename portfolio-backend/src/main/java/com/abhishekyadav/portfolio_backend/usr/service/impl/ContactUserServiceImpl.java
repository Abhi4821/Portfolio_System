package com.abhishekyadav.portfolio_backend.usr.service.impl;

import com.abhishekyadav.portfolio_backend.common.entity.ContactMessageEntity;
import com.abhishekyadav.portfolio_backend.common.repository.ContactMessageRepository;
import com.abhishekyadav.portfolio_backend.usr.service.ContactUserService;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ContactUserServiceImpl implements ContactUserService {

    private final ContactMessageRepository contactRepository;
    private final JavaMailSender mailSender;

    public ContactUserServiceImpl(ContactMessageRepository contactRepository,
                                  JavaMailSender mailSender) {
        this.contactRepository = contactRepository;
        this.mailSender = mailSender;
    }

    @Override
    public ContactMessageEntity submitMessage(ContactMessageEntity message) {

        /* 1️⃣ EMAIL FORMAT VALIDATION */

        if (!message.getSenderEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new RuntimeException("Invalid email format");
        }

        /* 2️⃣ TRY AUTO-REPLY FIRST (EMAIL EXIST CHECK) */

        try {

            SimpleMailMessage reply = new SimpleMailMessage();

            reply.setTo(message.getSenderEmail());
            reply.setSubject("Thanks for contacting me");

            reply.setText(
                    "Hi,\n\n" +
                            "Thanks for contacting me. I received your message.\n\n" +
                            "Regards,\n" +
                            "Abhishek Yadav"
            );

            mailSender.send(reply);

        } catch (Exception e) {

            throw new RuntimeException("Invalid email address or email does not exist");

        }

        /* 3️⃣ SAVE MESSAGE */

        message.setCreatedAt(LocalDateTime.now());

        ContactMessageEntity savedMessage = contactRepository.save(message);

        /* 4️⃣ SEND MAIL TO OWNER */

        try {

            SimpleMailMessage mail = new SimpleMailMessage();

            mail.setTo("abhi.dev2505@gmail.com");

            mail.setSubject("New Portfolio Contact Message");

            mail.setText(
                    "New message received from portfolio\n\n" +
                            "Email: " + message.getSenderEmail() + "\n" +
                            "Subject: " + message.getSubject() + "\n\n" +
                            "Message:\n" +
                            message.getMessage()
            );

            mailSender.send(mail);

        } catch (Exception e) {

            System.out.println("Owner email failed: " + e.getMessage());
        }

        return savedMessage;
    }
}