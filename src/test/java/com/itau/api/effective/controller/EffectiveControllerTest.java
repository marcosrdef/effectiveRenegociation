package com.itau.api.effective.controller;

import com.itau.api.effective.dto.EffectiveRequestDTO;
import com.itau.api.effective.dto.SimulationRequestDTO;
import com.itau.api.effective.mock.EffectiveMock;
import com.itau.api.effective.mock.SimulationMock;
import com.itau.api.effective.service.EffectiveRenegociationService;
import com.itau.api.effective.service.SimulationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class EffectiveControllerTest {

    @Mock
    private SimulationService simulationService;

    @Mock
    private EffectiveRenegociationService effectiveRenegociationService;

    @InjectMocks
    private EffectiveController effectiveController;

    @Test
    void simulations() {
        SimulationRequestDTO request = SimulationMock.getSimulationRequest();
        Mockito.when(simulationService.findByGroupSimulationId(request)).thenReturn(SimulationMock.getSimulationResponse());
        assertNotNull(effectiveController.simulation(request));
    }

    @Test
    void renegociation() {
        EffectiveRequestDTO request = EffectiveMock.getEffectiveRequest();
        Mockito.when(effectiveRenegociationService.findByTransactionId(request)).thenReturn(EffectiveMock.getEffectiveResponse());
        assertNotNull(effectiveController.renegociation(request));

    }
}
