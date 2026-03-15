package com.abhishekyadav.portfolio_backend.adi.service.impl;


import com.abhishekyadav.portfolio_backend.adi.service.ContactAdminService;
import com.abhishekyadav.portfolio_backend.common.entity.ContactMessageEntity;
import com.abhishekyadav.portfolio_backend.common.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactAdminServiceImpl implements ContactAdminService {

    private final ContactMessageRepository contactRepository;

    public ContactAdminServiceImpl(ContactMessageRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<ContactMessageEntity> getAllMessages() {
        return contactRepository.findAll();
    }

    @Override
    public void deleteMessage(Long id) {
        contactRepository.deleteById(id);
    }
}
