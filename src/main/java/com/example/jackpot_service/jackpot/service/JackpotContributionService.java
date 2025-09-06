package com.example.jackpot_service.jackpot.service;

import com.example.jackpot_service.bet.model.Bet;
import com.example.jackpot_service.jackpot.model.JackpotEntity;
import com.example.jackpot_service.jackpot.repository.JackpotRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Service for calculating jackpot contributions.
 */
@Service
public class JackpotContributionService {

    private final JackpotRepository jackpotRepository;
    private final List<JackpotContribCalculation> contribCalculationMethods;

    /**
     * Constructs a new JackpotContributionService with the given JackpotRepository and contribution calculation strategies.
     *
     * @param jackpotRepository                     The repository for accessing jackpot data.
     */
    public JackpotContributionService(JackpotRepository jackpotRepository,
                                      List<JackpotContribCalculation> contribCalculationMethods) {
        this.jackpotRepository = jackpotRepository;
        this.contribCalculationMethods = contribCalculationMethods;
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

        return jackpotContribCalculation.calculateContribution(betAmount, null);
    }

/*    public int storeContribution(Bet bet, int contribution) {

    }*/

}
