package com.example.jackpot_service.bet.consumer;

import com.example.jackpot_service.bet.model.Bet;
import com.example.jackpot_service.bet.model.BetEntity;
import com.example.jackpot_service.bet.repository.BetRepository;
import com.example.jackpot_service.jackpot.service.JackpotContributionService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka consumer for processing bet messages from the 'jackpot-bets' topic.
 * It logs the received messages and persists them to the database.
 */
@Component
public class BetKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(BetKafkaConsumer.class);
    private static final String TOPIC_NAME = "jackpot-bets";
    private static final String CONSUMER_GROUP_ID = "jackpot-bet-consumer-group";

    private final BetRepository betRepository;
    private final JackpotContributionService jackpotContributionService;

    /**
     * Constructs a new BetKafkaConsumer with the given BetRepository.
     *
     * @param betRepository The repository used to save Bet entities to the database.
     */
    public BetKafkaConsumer(BetRepository betRepository, JackpotContributionService jackpotContributionService) {
        this.betRepository = betRepository;
        this.jackpotContributionService = jackpotContributionService;
    }

    /**
     * Listens for messages on the 'jackpot-bets' Kafka topic.
     * If the consumer group does not exist, it will start consuming from the beginning of the topic.
     * Received messages (Bet objects) are deserialized by Kafka and then persisted to the database.
     *
     * @param consumerRecord The Kafka consumer record containing the key and value (Bet object) of the message.
     */
    @KafkaListener(topics = TOPIC_NAME, groupId = CONSUMER_GROUP_ID,
                   properties = {"auto.offset.reset=earliest"})
    public void listen(ConsumerRecord<String, Bet> consumerRecord) {
        Bet bet = consumerRecord.value();
        log.info("Received Kafka message - Topic: {}, Key: {}, Value: {}, Partition: {}, Offset: {}",
                consumerRecord.topic(),
                consumerRecord.key(),
                bet,
                consumerRecord.partition(),
                consumerRecord.offset());

        try {
            BetEntity betEntity = new BetEntity(bet.id(), bet.userId(), bet.jackpotId(), bet.amount());
            betRepository.save(betEntity);
            log.info("Saved BetEntity to database: {}", betEntity);

            int contribution = jackpotContributionService.calculateContribution(betEntity.getJackpotId(), betEntity.getAmount());


        } catch (Exception e) {
            log.error("Error saving BetEntity to database: {}", bet, e);
        }
    }
}
