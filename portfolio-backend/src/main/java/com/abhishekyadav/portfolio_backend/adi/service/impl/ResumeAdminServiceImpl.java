package com.abhishekyadav.portfolio_backend.adi.service.impl;


import com.abhishekyadav.portfolio_backend.adi.service.ResumeAdminService;
import com.abhishekyadav.portfolio_backend.common.entity.ResumeEntity;
import com.abhishekyadav.portfolio_backend.common.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ResumeAdminServiceImpl implements ResumeAdminService {
    @Value("${file.upload-dir}")
    private String uploadRoot;
    private final ResumeRepository resumeRepository;

    public ResumeAdminServiceImpl(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }


    public ResumeEntity uploadResume(MultipartFile file, ResumeEntity resume) {
        try {
            String uploadDir = uploadRoot + "/resume/";
            Files.createDirectories(Paths.get(uploadDir));

            ResumeEntity existing = resumeRepository.findAll()
                    .stream()
                    .findFirst()
                    .orElse(null);

            if (existing != null) {
                // delete old file
                deleteResumeFile(existing.getResumeUrl());
                // delete old DB record
                resumeRepository.deleteById(existing.getId());
            }
            String originalName = file.getOriginalFilename();
            String extension = originalName.substring(originalName.lastIndexOf("."));
            String fileName = UUID.randomUUID() + extension;

            Path filePath = Paths.get(uploadDir + fileName);
            Files.write(filePath, file.getBytes());

            //  DB me sirf path / URL
            resume.setResumeUrl("/uploads/resume/" + fileName);
            resume.setUploadedAt(LocalDateTime.now());

            return resumeRepository.save(resume);

        } catch (IOException e) {
            throw new RuntimeException("Resume upload failed", e);
        }
    }


    @Override
    public void deleteResume(Long resumeId) {

        ResumeEntity resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));
        deleteResumeFile(resume.getResumeUrl());
        resumeRepository.deleteById(resumeId);
    }



    private void deleteResumeFile(String resumeUrl) {
        if (resumeUrl == null || resumeUrl.isBlank()) return;

        try {
            // URL → filename
            String fileName = resumeUrl.substring(resumeUrl.lastIndexOf("/") + 1);
            // actual disk path
            Path filePath = Paths.get(uploadRoot + "/resume/" + fileName);
            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete resume file", e);
        }
    }


}
