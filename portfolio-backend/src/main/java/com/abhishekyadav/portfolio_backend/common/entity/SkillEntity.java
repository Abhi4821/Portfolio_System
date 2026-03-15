package com.abhishekyadav.portfolio_backend.common.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "skills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "skill_name", nullable = false)
    private String skillName;

    @Column(name = "skill_level")
    private String skillLevel;

    @Column(name = "skill_image_url", columnDefinition = "TEXT")
    private String skillImageUrl;

    @Column(name = "certificate_url", columnDefinition = "TEXT")
    private String certificateUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
