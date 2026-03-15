package com.abhishekyadav.portfolio_backend.common.repository;


import com.abhishekyadav.portfolio_backend.common.entity.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeEntity, Long> {
}
