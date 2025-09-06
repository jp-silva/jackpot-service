package com.example.jackpot_service.bet.controller;

import com.example.jackpot_service.bet.model.Bet;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.jackpot_service.bet.service.BetService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

/**
 * REST controller for managing bet-related operations.
 * Provides endpoints for placing new bets and retrieving existing bets.
 */
@RestController
@RequestMapping("/v1/bets")
public class BetController {

    private static final Logger log = LoggerFactory.getLogger(BetController.class);
    private final BetService betService;

    /**
     * Constructs a new BetController with the given BetService.
     *
     * @param betService The service responsible for handling bet-related business logic.
     */
    public BetController(BetService betService) {
        this.betService = betService;
    }

    /**
     * Receives a new bet, processes it asynchronously via the BetService, and returns a 202 Accepted response.
     * The response includes a Location header pointing to the URI of the newly created bet.
     *
     * @param bet The bet object containing details like id, userId, jackpotId, and amount.
     * @return A ResponseEntity with status 202 Accepted and a Location header.
     */
    @PostMapping
    public ResponseEntity<Void> placeBet(@Valid @RequestBody Bet bet) {
        log.info("Received bet: {} - Processing asynchronously.", bet);

        betService.placeBet(bet);

        // Construct the location URI for the newly created bet
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(bet.id())
                .toUri();

        return ResponseEntity.accepted().location(location).build();
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
