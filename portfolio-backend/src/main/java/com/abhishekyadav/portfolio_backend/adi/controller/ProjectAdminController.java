package com.abhishekyadav.portfolio_backend.adi.controller;


import com.abhishekyadav.portfolio_backend.adi.service.ProjectAdminService;
import com.abhishekyadav.portfolio_backend.common.entity.ProjectEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.nio.file.*;
import java.util.UUID;
import java.io.IOException;

@RestController
@RequestMapping("/api/adi/projects")
@CrossOrigin
public class ProjectAdminController {
    private final ProjectAdminService projectService;

    public ProjectAdminController(ProjectAdminService projectService) {
        this.projectService = projectService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ProjectEntity addProject(
            @RequestParam String projectTitle,
            @RequestParam String projectDescription,
            @RequestParam String sourceCodeUrl,
            @RequestParam String demoUrl,
            @RequestParam(required = false) MultipartFile image
    ) {

        return projectService.addProject(
                projectTitle,
                projectDescription,
                sourceCodeUrl,
                demoUrl,
                image
        );
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }

}







