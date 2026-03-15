package com.abhishekyadav.portfolio_backend.adi.service;


import com.abhishekyadav.portfolio_backend.common.entity.ProjectEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ProjectAdminService {

    ProjectEntity addProject(
            String projectTitle,
            String projectDescription,
            String sourceCodeUrl,
            String demoUrl,
            MultipartFile image
    );

    void deleteProject(Long projectId);
}
