package com.abhishekyadav.portfolio_backend.usr.controller;


import com.abhishekyadav.portfolio_backend.common.entity.ProjectEntity;
import com.abhishekyadav.portfolio_backend.usr.service.ProjectUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usr/projects")
@CrossOrigin
public class ProjectUserController {

    private final ProjectUserService projectService;

    public ProjectUserController(ProjectUserService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectEntity> getAllProjects() {
        return projectService.getAllProjects();
    }
}

