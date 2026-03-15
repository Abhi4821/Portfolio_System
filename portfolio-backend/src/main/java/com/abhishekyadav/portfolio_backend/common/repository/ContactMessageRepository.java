package com.abhishekyadav.portfolio_backend.common.repository;


import com.abhishekyadav.portfolio_backend.common.entity.ContactMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessageEntity, Long> {
}
