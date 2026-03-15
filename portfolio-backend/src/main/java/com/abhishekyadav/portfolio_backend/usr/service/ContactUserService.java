package com.abhishekyadav.portfolio_backend.usr.service;


import com.abhishekyadav.portfolio_backend.common.entity.ContactMessageEntity;

public interface ContactUserService {

    ContactMessageEntity submitMessage(ContactMessageEntity message);
}
