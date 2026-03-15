package com.abhishekyadav.portfolio_backend.common.repository;


import com.abhishekyadav.portfolio_backend.common.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
}
