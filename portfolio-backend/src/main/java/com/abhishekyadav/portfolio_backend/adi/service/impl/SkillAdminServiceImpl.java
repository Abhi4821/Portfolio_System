package com.abhishekyadav.portfolio_backend.adi.service.impl;

import com.abhishekyadav.portfolio_backend.adi.service.SkillAdminService;
import com.abhishekyadav.portfolio_backend.common.entity.SkillEntity;
import com.abhishekyadav.portfolio_backend.common.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;
@Service
public class SkillAdminServiceImpl implements SkillAdminService {

    @Value("${file.upload-dir}")
    private String uploadRoot;
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
            String uploadDir = uploadRoot + "/certificates/";
            Files.createDirectories(Paths.get(uploadDir));

            String originalName = file.getOriginalFilename();
            String extension = originalName.substring(originalName.lastIndexOf("."));
            String fileName = UUID.randomUUID() + extension;

            Path filePath = Paths.get(uploadDir + fileName);
            Files.write(filePath, file.getBytes());

            return "/uploads/certificates/" + fileName;

        } catch (Exception e) {
            throw new RuntimeException("Certificate upload failed", e);
        }
    }

    @Override
    public void deleteSkill(Long skillId) {

        SkillEntity skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
        deleteCertificateFile(skill.getCertificateUrl());
        skillRepository.deleteById(skillId);
    }
    private void deleteCertificateFile(String certificateUrl) {
        if (certificateUrl == null || certificateUrl.isBlank()) return;

        try {
            String fileName = certificateUrl.substring(certificateUrl.lastIndexOf("/") + 1);
            Path filePath = Paths.get(uploadRoot + "/certificates/" + fileName);
            Files.deleteIfExists(filePath);

        } catch (Exception e) {
            throw new RuntimeException("Failed to delete certificate file", e);
        }
    }


}
