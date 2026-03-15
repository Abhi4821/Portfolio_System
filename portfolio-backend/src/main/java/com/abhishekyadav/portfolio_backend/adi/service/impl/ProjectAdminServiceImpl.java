package com.abhishekyadav.portfolio_backend.adi.service.impl;


import com.abhishekyadav.portfolio_backend.adi.service.ProjectAdminService;
import com.abhishekyadav.portfolio_backend.common.entity.ProjectEntity;
import com.abhishekyadav.portfolio_backend.common.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ProjectAdminServiceImpl implements ProjectAdminService {
    private final ProjectRepository projectRepository;
    public ProjectAdminServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }



    @Override
    public ProjectEntity addProject(
            String projectTitle,
            String projectDescription,
            String sourceCodeUrl,
            String demoUrl,
            MultipartFile image
    ) {
        String imageUrl = null;
        if (image != null && !image.isEmpty()) {
            imageUrl = saveProjectImage(image);
        }
        ProjectEntity project = new ProjectEntity();
        project.setProjectTitle(projectTitle);
        project.setProjectDescription(projectDescription);
        project.setSourceCodeUrl(sourceCodeUrl);
        project.setDemoUrl(demoUrl);
        project.setProjectImageUrl(imageUrl);

        return projectRepository.save(project);
    }
    private String saveProjectImage(MultipartFile image) {
        if (image == null || image.isEmpty()) {
            return null;
        }
        try {
            String uploadDir = "D:/portfolio_uploads/portfolio_uploads/projects/";
            Files.createDirectories(Paths.get(uploadDir));

            String originalName = image.getOriginalFilename();
            String extension = originalName.substring(originalName.lastIndexOf("."));
            String fileName = UUID.randomUUID() + extension;

            Path filePath = Paths.get(uploadDir + fileName);
            Files.write(filePath, image.getBytes());
            return "http://localhost:8081/uploads/projects/" + fileName;

        } catch (Exception e) {
            throw new RuntimeException("Project image upload failed", e);
        }
    }

    @Override
    public void deleteProject(Long projectId) {
        ProjectEntity project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        // 1️ image delete
        deleteImageFile(project.getProjectImageUrl());
        // 2️ db delete
        projectRepository.deleteById(projectId);
    }

    private void deleteImageFile(String imageUrl) {
        if (imageUrl == null || imageUrl.isBlank()){
            return;
        }
        try {
            String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
            Path filePath = Paths.get(
                    "D:/portfolio_uploads/portfolio_uploads/projects/", fileName
            );
            System.out.println("Deleting: " + filePath);
            Files.deleteIfExists(filePath);

        } catch (Exception e) {
            throw new RuntimeException("Failed to delete image file", e);
        }
    }
}
