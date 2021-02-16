package com.itau.api.effective.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.effective.factory.EffectiveRenegociationFactory;
import com.itau.api.effective.factory.SimulationFactory;
import com.itau.api.effective.model.EffectiveRenegociationModel;
import com.itau.api.effective.model.SimulationModel;
import com.itau.api.effective.service.EffectiveRenegociationService;
import com.itau.api.effective.service.SimulationService;
import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerRenegociationServiceImpl {

    private final Logger logger = LoggerFactory.getLogger(Producer.class);
    private final SimulationService simulationService;
    private final SimulationFactory simulationFactory;
    private final EffectiveRenegociationService effectiveRenegociationService;
    private final EffectiveRenegociationFactory effectiveRenegociationFactory;

    public ConsumerRenegociationServiceImpl(final SimulationService simulationService,
                                            final SimulationFactory simulationFactory,
                                            final EffectiveRenegociationService effectiveRenegociationService,
                                            final EffectiveRenegociationFactory effectiveRenegociationFactory) {
        this.simulationService = simulationService;
        this.simulationFactory = simulationFactory;
        this.effectiveRenegociationService = effectiveRenegociationService;
        this.effectiveRenegociationFactory = effectiveRenegociationFactory;
    }

    @KafkaListener(topics = {"simulacaoRenegociacao"})
    public void consumerSimulacao(final @Payload String message,
                                  final @Header(KafkaHeaders.OFFSET) Integer offset,
                                  final @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                  final @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                  final @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                  final @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts
    ) throws Exception {
        logger.info(String.format("#### -> Consumed message -> TIMESTAMP: %d\n%s\noffset: %d\nkey: %s\npartition: %d\ntopic: %s", ts, message, offset, key, partition, topic));
        List<SimulationModel> lstModels = simulationService.generate(simulationFactory.convertStringToSimulateRequest(message));
        logger.info("#### simulation processed -> %s", lstModels);
    }

    @KafkaListener(topics = {"efetivarRenegociacao"})
    public void consumerEfetivacao(final @Payload String message,
                                   final @Header(KafkaHeaders.OFFSET) Integer offset,
                                   final @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                   final @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                   final @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                   final @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts
    ) throws JsonProcessingException {
        logger.info(String.format("#### -> Consumed message -> TIMESTAMP: %d\n%s\noffset: %d\nkey: %s\npartition: %d\ntopic: %s", ts, message, offset, key, partition, topic));
        EffectiveRenegociationModel effectiveRenegociationModel = effectiveRenegociationService.effective(
                                                                        effectiveRenegociationFactory.convertStringToEffectiveRequest(message));
        logger.info("#### renegociation processed -> %s", effectiveRenegociationModel);
    }
}
