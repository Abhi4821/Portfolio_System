package com.abhishekyadav.portfolio_backend.adi.controller;


import com.abhishekyadav.portfolio_backend.adi.service.ContactAdminService;
import com.abhishekyadav.portfolio_backend.common.entity.ContactMessageEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adi/messages")
@CrossOrigin
public class ContactAdminController {
    private final ContactAdminService contactService;
    public ContactAdminController(ContactAdminService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<ContactMessageEntity> getAllMessages() {
        return contactService.getAllMessages();
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        contactService.deleteMessage(id);
    }
}
