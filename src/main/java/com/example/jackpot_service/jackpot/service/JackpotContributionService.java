package com.example.jackpot_service.jackpot.service;

import com.example.jackpot_service.jackpot.model.JackpotEntity;
import com.example.jackpot_service.jackpot.repository.JackpotRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Service for calculating jackpot contributions.
 */
@Service
public class JackpotContributionService {

    private final JackpotRepository jackpotRepository;

    /**
     * Constructs a new JackpotContributionService with the given JackpotRepository.
     *
     * @param jackpotRepository The repository for accessing jackpot data.
     */
    public JackpotContributionService(JackpotRepository jackpotRepository) {
        this.jackpotRepository = jackpotRepository;
    }

    /**
     * Calculates the contribution of a bet to a specific jackpot.
     *
     * @param jackpotId The ID of the jackpot.
     * @param betAmount The amount of the bet.
     * @return The calculated contribution amount.
     * @throws NoSuchElementException if the jackpot with the given ID is not found.
     */
    public BigDecimal calculateContribution(UUID jackpotId, BigDecimal betAmount) {
        JackpotEntity jackpotEntity = jackpotRepository.findById(jackpotId)
                .orElseThrow(() -> new NoSuchElementException("Jackpot not found with ID: " + jackpotId));

        // TODO: Implement the actual contribution calculation logic based on the jackpot's contributionConfig
        // For now, returning a placeholder or a simple calculation.
        return BigDecimal.ZERO; // Placeholder
    }
}
