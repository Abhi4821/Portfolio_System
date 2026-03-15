package com.abhishekyadav.portfolio_backend.usr.controller;

import com.abhishekyadav.portfolio_backend.common.entity.ContactMessageEntity;
import com.abhishekyadav.portfolio_backend.usr.service.ContactUserService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usr/contact")
@CrossOrigin
public class ContactUserController {

    private final ContactUserService contactService;

    public ContactUserController(ContactUserService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<?> submitMessage(
            @Valid @RequestBody ContactMessageEntity message) {

        ContactMessageEntity savedMessage =
                contactService.submitMessage(message);

        return ResponseEntity.ok(savedMessage);
    }
}