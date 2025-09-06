package com.example.jackpot_service.bet.controller;

import com.example.jackpot_service.TestcontainersConfiguration;
import com.example.jackpot_service.bet.model.Bet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.Duration;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test for {@link BetController} that verifies the end-to-end flow of creating and retrieving bets.
 * This test uses {@link SpringBootTest} to load the full application context and {@link TestcontainersConfiguration}
 * to set up necessary infrastructure like Kafka and a database.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestcontainersConfiguration.class)
class BetIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Tests the end-to-end process of placing a bet and then waiting for it to be available via the GET API.
     * It uses Awaitility to poll the GET endpoint until the bet is successfully retrieved.
     *
     * @throws Exception if an error occurs during the mock MVC requests.
     */
    @Test
    @DisplayName("Should create a bet via POST and then retrieve it via GET after processing")
    void shouldCreateAndRetrieveBet() throws Exception {
        // Given a new bet
        UUID betId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID jackpotId = UUID.fromString("a277d806-1a18-4371-8fc2-442e713c140a");
        int amount = 100;
        Bet newBet = new Bet(betId, userId, jackpotId, amount);

        // When a POST request is made to create the bet
        mockMvc.perform(post("/v1/bets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newBet)))
                .andExpect(status().isAccepted());

        // Then the bet should eventually be retrievable via GET
        Awaitility.await()
                .atMost(Duration.ofSeconds(10))
                .pollInterval(Duration.ofMillis(500))
                .untilAsserted(() -> {
                    MvcResult getResult = mockMvc.perform(get("/v1/bets/{betId}", betId)
                                    .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andReturn();

                    String responseContent = getResult.getResponse().getContentAsString();
                    Bet retrievedBet = objectMapper.readValue(responseContent, Bet.class);

                    assertThat(retrievedBet).isEqualTo(newBet);
                });
    }
}
