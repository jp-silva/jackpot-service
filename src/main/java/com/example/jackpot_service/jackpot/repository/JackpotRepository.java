package com.example.jackpot_service.jackpot.repository;

import com.example.jackpot_service.jackpot.model.JackpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing {@link JackpotEntity} entities.
 */
@Repository
public interface JackpotRepository extends JpaRepository<JackpotEntity, UUID> {
}
