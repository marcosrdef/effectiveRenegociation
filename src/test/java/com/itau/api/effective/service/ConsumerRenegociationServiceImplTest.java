package com.itau.api.effective.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.effective.dto.EffectiveRequestDTO;
import com.itau.api.effective.dto.SimulationRequestDTO;
import com.itau.api.effective.factory.EffectiveRenegociationFactory;
import com.itau.api.effective.factory.SimulationFactory;
import com.itau.api.effective.mock.EffectiveMock;
import com.itau.api.effective.mock.SimulationMock;
import com.itau.api.effective.service.impl.ConsumerRenegociationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(MockitoExtension.class)
public class ConsumerRenegociationServiceImplTest {

    @Mock
    private Logger logger = LoggerFactory.getLogger(ConsumerRenegociationServiceImpl.class);

    @Mock
    private SimulationService simulationService;

    @Mock
    private SimulationFactory simulationFactory;

    @Mock
    private EffectiveRenegociationService effectiveRenegociationService;

    @Mock
    private EffectiveRenegociationFactory effectiveRenegociationFactory;

    @InjectMocks
    private ConsumerRenegociationServiceImpl consumerRenegociationServiceImpl;

    @Test
    void testConsumerSimulation() throws Exception {
        String key = "701e8636-7cad-4569-9816-7228db6bdf96";
        int offset = 0;
        int partition = 0;
        String topic = "simulacaoRenegociacao";
        long ts = System.currentTimeMillis();
        SimulationRequestDTO request = SimulationMock.getSimulationRequest();
        consumerRenegociationServiceImpl.consumerSimulation(SimulationMock.getMessageSimulation()
                ,offset, key, partition, topic, ts);
    }

    @Test
    void testConsumerEfetivation() throws JsonProcessingException {
        String key = "701e8636-7cad-4569-9816-7228db6bdf97";
        int offset = 0;
        int partition = 0;
        String topic = "efetivarRenegociacao";
        long ts = System.currentTimeMillis();
        EffectiveRequestDTO request = EffectiveMock.getEffectiveRequest();
        consumerRenegociationServiceImpl.consumerEfetivation(EffectiveMock.getMessageEffective(),
                offset, key, partition, topic, ts);
    }
}
