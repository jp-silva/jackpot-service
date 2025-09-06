package com.example.jackpot_service.bet.service;

import com.example.jackpot_service.bet.model.Bet;
import com.example.jackpot_service.bet.model.BetEntity;
import com.example.jackpot_service.bet.repository.BetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of {@link BetService} that publishes bets to a Kafka topic.
 */
@Service
public class AsyncBetService implements BetService {

    private static final Logger log = LoggerFactory.getLogger(AsyncBetService.class);
    private static final String TOPIC_NAME = "jackpot-bets";

    private final KafkaTemplate<String, Bet> kafkaTemplate;
    private final BetRepository betRepository;

    public AsyncBetService(KafkaTemplate<String, Bet> kafkaTemplate, BetRepository betRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.betRepository = betRepository;
    }

    /**
     * Publishes the given bet to the 'jackpot-bets' Kafka topic.
     * The jackpotId is used as the Kafka message key, and the Bet object is the message value.
     *
     * @param bet The bet object to be published.
     */
    @Override
    public void placeBet(Bet bet) {
        kafkaTemplate.send(TOPIC_NAME, bet.jackpotId().toString(), bet);
        log.info("Published bet to Kafka topic '{}' with key '{}': {}", TOPIC_NAME, bet.jackpotId(), bet);
    }

    /**
     * Retrieves a bet by its unique identifier.
     *
     * @param id The UUID of the bet to retrieve.
     * @return An {@link Optional} containing the {@link BetEntity} if found, or an empty Optional if not.
     */
    public Optional<Bet> getBetById(UUID id) {
        return betRepository.findById(id)
                            .map(betEntity -> new Bet(betEntity.getId(), betEntity.getUserId(), betEntity.getJackpotId(), betEntity.getAmount()));
    }
}
