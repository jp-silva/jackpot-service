package com.example.jackpot_service.bet.controller;

import com.example.jackpot_service.bet.model.Bet;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST controller for managing bet-related operations.
 * Provides endpoints for placing new bets and retrieving existing bets.
 */
@RestController
@RequestMapping("/v1/bets")
public class BetController {

    private static final Logger log = LoggerFactory.getLogger(BetController.class);

    /**
     * Receives a new bet, logs its details, and returns a confirmation.
     *
     * @param bet The bet object containing details like id, userId, jackpotId, and amount.
     * @return A ResponseEntity indicating the successful receipt of the bet.
     */
    @PostMapping
    public ResponseEntity<Void> placeBet(@Valid @RequestBody Bet bet) {
        log.info("Received bet: {}", bet);
        // In a real application, this would involve saving the bet to a database
        // and potentially publishing an event to Kafka.
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves a bet by its unique identifier.
     * For demonstration purposes, this method returns a dummy bet.
     *
     * @param betId The unique identifier of the bet to retrieve.
     * @return A ResponseEntity containing the requested bet, or not found if the bet does not exist.
     */
    @GetMapping("/{betId}")
    public ResponseEntity<Bet> getBet(@PathVariable UUID betId) {
        log.info("Attempting to retrieve bet with ID: {}", betId);
        // In a real application, this would involve fetching the bet from a database.
        // For now, we return a dummy bet.
        Bet dummyBet = new Bet(betId, UUID.randomUUID(), UUID.randomUUID(), 100);
        return ResponseEntity.ok(dummyBet);
    }
}
