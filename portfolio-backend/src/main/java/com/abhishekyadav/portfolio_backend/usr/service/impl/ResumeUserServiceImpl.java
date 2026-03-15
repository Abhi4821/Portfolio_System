package com.abhishekyadav.portfolio_backend.usr.service.impl;


import com.abhishekyadav.portfolio_backend.common.entity.ResumeEntity;
import com.abhishekyadav.portfolio_backend.common.repository.ResumeRepository;
import com.abhishekyadav.portfolio_backend.usr.service.ResumeUserService;
import org.springframework.stereotype.Service;

@Service
public class ResumeUserServiceImpl implements ResumeUserService {

    private final ResumeRepository resumeRepository;

    public ResumeUserServiceImpl(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Override
    public ResumeEntity getLatestResume() {
        return resumeRepository.findAll()
                .stream()
                .findFirst()
                .orElse(null);
    }
}
