package com.abhishekyadav.portfolio_backend.adi.service.impl;

import com.abhishekyadav.portfolio_backend.adi.service.SkillAdminService;
import com.abhishekyadav.portfolio_backend.common.entity.SkillEntity;
import com.abhishekyadav.portfolio_backend.common.repository.SkillRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;
@Service
public class SkillAdminServiceImpl implements SkillAdminService {

    private final SkillRepository skillRepository;

    public SkillAdminServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public SkillEntity addSkill(String skillName, String skillLevel, MultipartFile certificate) {
        SkillEntity skill = new SkillEntity();
        skill.setSkillName(skillName);
        skill.setSkillLevel(skillLevel);
        skill.setCreatedAt(LocalDateTime.now());

        if (certificate != null && !certificate.isEmpty()) {
            String certificateUrl = saveCertificateAndGetUrl(certificate);
            skill.setCertificateUrl(certificateUrl);
        }

        return skillRepository.save(skill);
    }

    private String saveCertificateAndGetUrl(MultipartFile file) {
        try {
            String uploadDir = "D:/portfolio_uploads/portfolio_uploads/certificates/";
            Files.createDirectories(Paths.get(uploadDir));

            String originalName = file.getOriginalFilename();
            String extension = originalName.substring(originalName.lastIndexOf("."));
            String fileName = UUID.randomUUID() + extension;

            Path filePath = Paths.get(uploadDir + fileName);
            Files.write(filePath, file.getBytes());

            return "http://localhost:8081/uploads/certificates/" + fileName;

        } catch (Exception e) {
            throw new RuntimeException("Certificate upload failed", e);
        }
    }

    @Override
    public void deleteSkill(Long skillId) {

        SkillEntity skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
        // 1️ certificate file delete
        deleteCertificateFile(skill.getCertificateUrl());
        // 2️ db delete
        skillRepository.deleteById(skillId);
    }
    private void deleteCertificateFile(String certificateUrl) {
        if (certificateUrl == null || certificateUrl.isBlank()) return;

        try {
            // URL → filename
            String fileName = certificateUrl.substring(certificateUrl.lastIndexOf("/") + 1);
            // actual disk path
            Path filePath = Paths.get(
                    "D:/portfolio_uploads/portfolio_uploads/certificates/" + fileName
            );
            Files.deleteIfExists(filePath);

        } catch (Exception e) {
            throw new RuntimeException("Failed to delete certificate file", e);
        }
    }


}
