package com.example.jackpot_service.jackpot.service;

import com.example.jackpot_service.bet.model.Bet;
import com.example.jackpot_service.common.JackpotProperties;
import com.example.jackpot_service.jackpot.model.JackpotContributionEntity;
import com.example.jackpot_service.jackpot.model.JackpotEntity;
import com.example.jackpot_service.jackpot.repository.JackpotContributionRepository;
import com.example.jackpot_service.jackpot.repository.JackpotRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

/**
 * Service for calculating jackpot contributions.
 */
@Service
public class JackpotContributionService {

    private final JackpotRepository jackpotRepository;
    private final JackpotContributionRepository jackpotContributionRepository;
    private final List<JackpotContribCalculation> contribCalculationMethods;
    private final JackpotProperties jackpotProperties;

    /**
     * Constructs a new JackpotContributionService with the given JackpotRepository and contribution calculation strategies.
     *
     * @param jackpotRepository                     The repository for accessing jackpot data.
     */
    public JackpotContributionService(JackpotRepository jackpotRepository, JackpotContributionRepository jackpotContributionRepository,
                                      List<JackpotContribCalculation> contribCalculationMethods, JackpotProperties jackpotProperties) {
        this.jackpotRepository = jackpotRepository;
        this.jackpotContributionRepository = jackpotContributionRepository;
        this.contribCalculationMethods = contribCalculationMethods;
        this.jackpotProperties = jackpotProperties;
    }

    /**
     * Calculates the contribution of a bet to a specific jackpot.
     *
     * @param jackpotId The ID of the jackpot.
     * @param betAmount The amount of the bet.
     * @return The calculated contribution amount.
     * @throws NoSuchElementException if the jackpot with the given ID is not found.
     */
    public int calculateContribution(UUID jackpotId, int betAmount) {
        JackpotEntity jackpotEntity = jackpotRepository.findById(jackpotId)
                .orElseThrow(() -> new NoSuchElementException("Jackpot not found with ID: " + jackpotId));


        JackpotContribCalculation jackpotContribCalculation = contribCalculationMethods.stream().filter(cm -> jackpotEntity.getContributionConfig().equals(cm.type()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No contribution calculation method found for type: " + jackpotEntity.getContributionConfig()));

        return jackpotContribCalculation.calculateContribution(betAmount, getCurrentJackpotPoolAmount(jackpotId));
    }

    public void storeContribution(Bet bet, int contribution) {
        int currentJackpotPoolAmount = getCurrentJackpotPoolAmount(bet.jackpotId());

        jackpotContributionRepository.save(new JackpotContributionEntity(bet.jackpotId(), bet.id(), bet.userId(), contribution, currentJackpotPoolAmount + contribution,LocalDateTime.now()));
    }

    private int getCurrentJackpotPoolAmount(UUID jackpotId) {
        return jackpotContributionRepository.findTopByJackpotIdOrderByCreatedAtDesc(jackpotId)
                .map(JackpotContributionEntity::getCurrentJackpotAmount)
                .orElse(jackpotProperties.getInitialPoolAmount());
    }
}
