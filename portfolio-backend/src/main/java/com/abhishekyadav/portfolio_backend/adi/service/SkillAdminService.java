package com.abhishekyadav.portfolio_backend.adi.service;

import com.abhishekyadav.portfolio_backend.common.entity.SkillEntity;
import org.springframework.web.multipart.MultipartFile;

public interface SkillAdminService {

    SkillEntity addSkill(String skillName,
                         String skillLevel,
                         MultipartFile certificate);

    void deleteSkill(Long skillId);
}
