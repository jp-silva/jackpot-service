package com.example.jackpot_service.bet.service;

import com.example.jackpot_service.bet.model.Bet;

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
}
