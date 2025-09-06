package com.example.jackpot_service.bet.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Represents a bet placed by a user on a jackpot.
 *
 * @param id The unique identifier of the bet.
 * @param userId The unique identifier of the user who placed the bet.
 * @param jackpotId The unique identifier of the jackpot the bet is placed on.
 * @param amount The amount of the bet in the currency's smallest unit.
 */
public record Bet(@NotNull UUID id, @NotNull UUID userId, @NotNull UUID jackpotId, @Min(1) int amount) {
}
