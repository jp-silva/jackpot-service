package com.example.jackpot_service.jackpot.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * An implementation of {@link JackpotContribCalculation} that returns a fixed percentage (1%) of the bet amount
 * as the jackpot contribution.
 */
@Service
public class FixedJackpotContributionCalculation implements JackpotContribCalculation {

    private static final BigDecimal FIXED_CONTRIBUTION_PERCENTAGE = BigDecimal.valueOf(0.01); // 1%

    /**
     * Calculates the jackpot contribution as a fixed 1% of the bet amount.
     *
     * @param betAmount The amount of the bet.
     * @param jackpotPoolAmount The current total amount in the jackpot pool (not used in this calculation).
     * @return The calculated jackpot contribution, which is 1% of the bet amount.
     */
    @Override
    public int calculateContribution(int betAmount, BigDecimal jackpotPoolAmount) {
        return BigDecimal.valueOf(betAmount).multiply(FIXED_CONTRIBUTION_PERCENTAGE).setScale(0, RoundingMode.HALF_UP).intValue();
    }

    @Override
    public String type() {
        return "FIXED_PERCENTAGE";
    }
}
