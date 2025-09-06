package com.example.jackpot_service.bet.repository;

import com.example.jackpot_service.bet.model.BetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing {@link BetEntity} instances in the database.
 * Provides standard CRUD operations for Bet entities.
 */
@Repository
public interface BetRepository extends JpaRepository<BetEntity, UUID> {
}
