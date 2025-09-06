package com.example.jackpot_service.jackpot.repository;

import com.example.jackpot_service.jackpot.model.JackpotContributionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing {@link JackpotContributionEntity} instances.
 * Provides standard JPA operations for JackpotContribution entities.
 */
@Repository
public interface JackpotContributionRepository extends JpaRepository<JackpotContributionEntity, UUID> {

    /**
     * Finds the latest jackpot contribution for a given jackpot ID, ordered by creation timestamp in descending order.
     *
     * @param jackpotId The ID of the jackpot.
     * @return An {@link Optional} containing the latest {@link JackpotContributionEntity}, or empty if none is found.
     */
    Optional<JackpotContributionEntity> findTopByJackpotIdOrderByCreatedAtDesc(UUID jackpotId);
}
