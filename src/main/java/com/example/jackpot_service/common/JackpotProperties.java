package com.example.jackpot_service.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for jackpot settings.
 * This class binds properties prefixed with 'jackpot' from the application configuration.
 */
@Component
@ConfigurationProperties(prefix = "jackpot")
public class JackpotProperties {

    /**
     * The initial amount for a new jackpot pool.
     */
    private int initialPoolAmount;

    /**
     * Returns the initial pool amount for a new jackpot.
     * @return The initial pool amount.
     */
    public int getInitialPoolAmount() {
        return initialPoolAmount;
    }

    /**
     * Sets the initial pool amount for a new jackpot.
     * @param initialPoolAmount The initial pool amount to set.
     */
    public void setInitialPoolAmount(int initialPoolAmount) {
        this.initialPoolAmount = initialPoolAmount;
    }
}
