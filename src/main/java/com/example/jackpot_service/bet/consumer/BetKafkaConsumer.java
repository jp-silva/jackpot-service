package com.example.jackpot_service.bet.consumer;

import com.example.jackpot_service.bet.model.Bet;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka consumer for processing bet messages from the 'jackpot-bets' topic.
 * It logs the received messages.
 */
@Component
public class BetKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(BetKafkaConsumer.class);
    private static final String TOPIC_NAME = "jackpot-bets";
    private static final String CONSUMER_GROUP_ID = "jackpot-bet-consumer-group";

    /**
     * Listens for messages on the 'jackpot-bets' Kafka topic.
     * If the consumer group does not exist, it will start consuming from the beginning of the topic.
     *
     * @param consumerRecord The Kafka consumer record containing the key and value of the message.
     */
    @KafkaListener(topics = TOPIC_NAME, groupId = CONSUMER_GROUP_ID, 
                   properties = {"auto.offset.reset=earliest"})
    public void listen(ConsumerRecord<String, Bet> consumerRecord) {
        log.info("Received Kafka message - Topic: {}, Key: {}, Value: {}, Partition: {}, Offset: {}",
                consumerRecord.topic(),
                consumerRecord.key(),
                consumerRecord.value(),
                consumerRecord.partition(),
                consumerRecord.offset());
    }
}
