package com.abhishekyadav.portfolio_backend.usr.service.impl;


import com.abhishekyadav.portfolio_backend.common.entity.SkillEntity;
import com.abhishekyadav.portfolio_backend.common.repository.SkillRepository;
import com.abhishekyadav.portfolio_backend.usr.service.SkillUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillUserServiceImpl implements SkillUserService {

    private final SkillRepository skillRepository;

    public SkillUserServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<SkillEntity> getAllSkills() {
        return skillRepository.findAll();
    }
}
