package com.example.jackpot_service.jackpot.service;

import java.math.BigDecimal;

/**
 * Interface for calculating the jackpot contribution based on bet amount and current jackpot pool.
 */
public interface JackpotContribCalculation {

    /**
     * Calculates the jackpot contribution.
     *
     * @param betAmount The amount of the bet.
     * @param jackpotPoolAmount The current total amount in the jackpot pool.
     * @return The calculated jackpot contribution.
     */
    int calculateContribution(int betAmount, BigDecimal jackpotPoolAmount);

    String type();
}
