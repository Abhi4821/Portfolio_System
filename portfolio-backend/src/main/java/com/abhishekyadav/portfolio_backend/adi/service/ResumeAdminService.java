package com.abhishekyadav.portfolio_backend.adi.service;


import com.abhishekyadav.portfolio_backend.common.entity.ResumeEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ResumeAdminService {

    ResumeEntity uploadResume(MultipartFile file, ResumeEntity resume);

    void deleteResume(Long resumeId);
}
