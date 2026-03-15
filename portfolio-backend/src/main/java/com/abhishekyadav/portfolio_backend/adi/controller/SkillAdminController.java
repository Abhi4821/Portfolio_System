package com.abhishekyadav.portfolio_backend.adi.controller;


import com.abhishekyadav.portfolio_backend.adi.service.SkillAdminService;
import com.abhishekyadav.portfolio_backend.common.entity.SkillEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/adi/skills")
@CrossOrigin
public class SkillAdminController {
    private final SkillAdminService skillService;

    public SkillAdminController(SkillAdminService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public SkillEntity addSkill(
            @RequestParam("skillName") String skillName,
            @RequestParam(value = "skillLevel", required = false) String skillLevel,
            @RequestParam(value = "certificate", required = false) MultipartFile certificate
    ){
        return skillService.addSkill(skillName, skillLevel, certificate);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
    }
}
