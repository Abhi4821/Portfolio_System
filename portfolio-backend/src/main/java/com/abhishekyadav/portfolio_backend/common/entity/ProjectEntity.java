package com.abhishekyadav.portfolio_backend.common.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_title", nullable = false)
    private String projectTitle;

    @Column(name = "project_description", columnDefinition = "TEXT")
    private String projectDescription;

    @Column(name = "project_image_url", columnDefinition = "TEXT")
    private String projectImageUrl;

    @Column(name = "source_code_url")
    private String sourceCodeUrl;

    @Column(name = "demo_url")
    private String demoUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
