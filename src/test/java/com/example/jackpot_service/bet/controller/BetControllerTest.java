package com.example.jackpot_service.bet.controller;

import com.example.jackpot_service.bet.model.Bet;
import com.example.jackpot_service.bet.service.BetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Slice test for {@link BetController} focusing on web layer validation.
 * Uses {@link WebMvcTest} to test the controller in isolation.
 */
@WebMvcTest(BetController.class)
class BetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BetService betService;

    /**
     * Tests that a valid Bet request is successfully processed and returns 202 Accepted with a Location header.
     *
     * @throws Exception if an error occurs during the mock MVC request.
     */
    @Test
    @DisplayName("Should accept a valid bet request and return 202 Accepted with Location header")
    void shouldAcceptValidBetRequest() throws Exception {
        Bet validBet = new Bet(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 100);
        doNothing().when(betService).placeBet(any(Bet.class));

        mockMvc.perform(post("/v1/bets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validBet)))
                .andExpect(status().isAccepted())
                .andExpect(header().exists("Location"));
    }

    /**
     * Tests that a Bet request with a null ID is rejected with a bad request status.
     *
     * @throws Exception if an error occurs during the mock MVC request.
     */
    @Test
    @DisplayName("Should reject bet request with null ID")
    void shouldRejectBetRequestWithNullId() throws Exception {
        Bet invalidBet = new Bet(null, UUID.randomUUID(), UUID.randomUUID(), 100);

        mockMvc.perform(post("/v1/bets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidBet)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Tests that a Bet request with a null userId is rejected with a bad request status.
     *
     * @throws Exception if an error occurs during the mock MVC request.
     */
    @Test
    @DisplayName("Should reject bet request with null userId")
    void shouldRejectBetRequestWithNullUserId() throws Exception {
        Bet invalidBet = new Bet(UUID.randomUUID(), null, UUID.randomUUID(), 100);

        mockMvc.perform(post("/v1/bets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidBet)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Tests that a Bet request with a null jackpotId is rejected with a bad request status.
     *
     * @throws Exception if an error occurs during the mock MVC request.
     */
    @Test
    @DisplayName("Should reject bet request with null jackpotId")
    void shouldRejectBetRequestWithNullJackpotId() throws Exception {
        Bet invalidBet = new Bet(UUID.randomUUID(), UUID.randomUUID(), null, 100);

        mockMvc.perform(post("/v1/bets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidBet)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Tests that a Bet request with an amount less than 1 is rejected with a bad request status.
     *
     * @throws Exception if an error occurs during the mock MVC request.
     */
    @Test
    @DisplayName("Should reject bet request with amount less than 1")
    void shouldRejectBetRequestWithAmountLessThanOne() throws Exception {
        Bet invalidBet = new Bet(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), 0);

        mockMvc.perform(post("/v1/bets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidBet)))
                .andExpect(status().isBadRequest());
    }
}
