package com.abhishekyadav.portfolio_backend.usr.controller;


import com.abhishekyadav.portfolio_backend.common.entity.SkillEntity;
import com.abhishekyadav.portfolio_backend.usr.service.SkillUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usr/skills")
@CrossOrigin
public class SkillUserController {

    private final SkillUserService skillService;

    public SkillUserController(SkillUserService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public List<SkillEntity> getAllSkills() {
        return skillService.getAllSkills();
    }
}
