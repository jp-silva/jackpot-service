package com.example.jackpot_service.bet.service;

import com.example.jackpot_service.bet.model.Bet;

import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for managing bet-related operations.
 */
public interface BetService {

    /**
     * Processes a new bet, typically by publishing it to a messaging system like Kafka.
     *
     * @param bet The bet object to be placed.
     */
    void placeBet(Bet bet);

    /**
     * Retrieves a bet by its unique identifier.
     *
     * @param betId The unique identifier of the bet to retrieve.
     * @return An Optional containing the Bet if found, or an empty Optional otherwise.
     */
    Optional<Bet> getBetById(UUID betId);
}
