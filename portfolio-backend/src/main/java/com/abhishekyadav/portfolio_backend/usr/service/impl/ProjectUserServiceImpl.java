package com.abhishekyadav.portfolio_backend.usr.service.impl;


import com.abhishekyadav.portfolio_backend.common.entity.ProjectEntity;
import com.abhishekyadav.portfolio_backend.common.repository.ProjectRepository;
import com.abhishekyadav.portfolio_backend.usr.service.ProjectUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectUserServiceImpl implements ProjectUserService {

    private final ProjectRepository projectRepository;

    public ProjectUserServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectEntity> getAllProjects() {
        return projectRepository.findAll();
    }
}
