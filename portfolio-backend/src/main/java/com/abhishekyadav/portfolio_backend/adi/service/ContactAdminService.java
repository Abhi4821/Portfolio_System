package com.abhishekyadav.portfolio_backend.adi.service;


import com.abhishekyadav.portfolio_backend.common.entity.ContactMessageEntity;

import java.util.List;

public interface ContactAdminService {
    List<ContactMessageEntity> getAllMessages();
    void deleteMessage(Long id);
}
