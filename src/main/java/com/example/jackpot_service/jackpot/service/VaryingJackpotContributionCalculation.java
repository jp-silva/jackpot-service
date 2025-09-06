package com.example.jackpot_service.jackpot.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * An implementation of {@link JackpotContribCalculation} that returns a varying percentage of the bet amount
 * as the jackpot contribution. The contribution percentage is higher when the jackpot pool is low and decreases
 * linearly as the jackpot pool increases, up to a certain threshold, after which it remains at a minimum.
 */
@Service
public class VaryingJackpotContributionCalculation implements JackpotContribCalculation {

    private static final BigDecimal MAX_CONTRIBUTION_PERCENTAGE = BigDecimal.valueOf(0.05); // 5%
    private static final BigDecimal MIN_CONTRIBUTION_PERCENTAGE = BigDecimal.valueOf(0.01); // 1%
    private static final BigDecimal JACKPOT_POOL_THRESHOLD = BigDecimal.valueOf(1000.00);

    /**
     * Calculates the jackpot contribution with a varying percentage based on the current jackpot pool amount.
     * The percentage starts at {@value #MAX_CONTRIBUTION_PERCENTAGE} and decreases linearly to
     * {@value #MIN_CONTRIBUTION_PERCENTAGE} as the jackpot pool grows up to {@value #JACKPOT_POOL_THRESHOLD}.
     * Beyond this threshold, the contribution remains at the minimum percentage.
     *
     * @param betAmount The amount of the bet.
     * @param jackpotPoolAmount The current total amount in the jackpot pool.
     * @return The calculated jackpot contribution.
     */
    @Override
    public int calculateContribution(int betAmount, BigDecimal jackpotPoolAmount) {
        BigDecimal effectiveContributionPercentage;
        BigDecimal betAmountDecimal = BigDecimal.valueOf(betAmount);

        if (jackpotPoolAmount.compareTo(BigDecimal.ZERO) < 0) {
            // Handle negative jackpot pool amount, perhaps throw an exception or default to max
            effectiveContributionPercentage = MAX_CONTRIBUTION_PERCENTAGE;
        } else if (jackpotPoolAmount.compareTo(JACKPOT_POOL_THRESHOLD) >= 0) {
            effectiveContributionPercentage = MIN_CONTRIBUTION_PERCENTAGE;
        } else {
            // Calculate the reduction factor based on how close the jackpot pool is to the threshold
            BigDecimal reductionFactor = jackpotPoolAmount.divide(JACKPOT_POOL_THRESHOLD, MathContext.DECIMAL128);
            BigDecimal percentageDifference = MAX_CONTRIBUTION_PERCENTAGE.subtract(MIN_CONTRIBUTION_PERCENTAGE);
            BigDecimal reductionAmount = percentageDifference.multiply(reductionFactor);
            effectiveContributionPercentage = MAX_CONTRIBUTION_PERCENTAGE.subtract(reductionAmount);
        }

        // Ensure the percentage is within the defined min and max bounds
        effectiveContributionPercentage = effectiveContributionPercentage.max(MIN_CONTRIBUTION_PERCENTAGE);
        effectiveContributionPercentage = effectiveContributionPercentage.min(MAX_CONTRIBUTION_PERCENTAGE);

        return betAmountDecimal.multiply(effectiveContributionPercentage).setScale(0, RoundingMode.HALF_UP).intValue();
    }

    @Override
    public String type() {
        return "VARYING_PERCENTAGE";
    }
}
