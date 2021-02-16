package com.itau.api.effective.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

public interface ConsumerRenegociationService {
    void consumerSimulation(final @Payload String message,
                      final @Header(KafkaHeaders.OFFSET) Integer offset,
                      final @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                      final @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                      final @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                      final @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) throws Exception;

    void consumerEfetivation(final @Payload String message,
                            final @Header(KafkaHeaders.OFFSET) Integer offset,
                            final @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                            final @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                            final @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                            final @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) throws JsonProcessingException;
}
