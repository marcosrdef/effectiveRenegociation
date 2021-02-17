package com.itau.api.effective.service;

import com.itau.api.effective.dto.SimulationRequestDTO;
import com.itau.api.effective.dto.SimulationResponseDTO;
import com.itau.api.effective.factory.SimulationFactory;
import com.itau.api.effective.mock.DebtMock;
import com.itau.api.effective.mock.SimulationMock;
import com.itau.api.effective.model.DebtModel;
import com.itau.api.effective.model.SimulationModel;
import com.itau.api.effective.service.impl.SimulationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class SimulationServiceImplTest {

    @InjectMocks
    private SimulationServiceImpl simulationServiceImpl;

    @Mock
    private SimulationModelService simulationModelService;

    @Mock
    private DebtsModelService debtsModelService;

    @Mock
    private SimulationFactory simulationFactory;

    @Test
    void testFindByDocumentId() {
        List<DebtModel> lstDebts = DebtMock.lstDebts();
        SimulationRequestDTO request = SimulationMock.getSimulationRequest();
        List<SimulationModel> LstSimulationModel = SimulationMock.getLstSimulationModel();
        SimulationResponseDTO simulationResponse = SimulationMock.getSimulationResponse();
        Mockito.when(simulationModelService.findByDocumentId(request)).thenReturn(Optional.of(LstSimulationModel));
        Mockito.when(debtsModelService.findDebtsByDocument(request)).thenReturn(Optional.of(lstDebts));
        Mockito.when(simulationFactory.convertListSimulationModelToSimulationResponse(LstSimulationModel,
                lstDebts)).thenReturn(simulationResponse);
        assertNotNull(simulationServiceImpl.findByDocumentId(request));
    }


    @Test
    void testFindByGroupSimulationId() {
        List<DebtModel> lstDebts = DebtMock.lstDebts();
        SimulationRequestDTO request = SimulationMock.getSimulationRequest();
        List<SimulationModel> LstSimulationModel = SimulationMock.getLstSimulationModel();
        SimulationResponseDTO simulationResponse = SimulationMock.getSimulationResponse();
        Mockito.when(simulationModelService.findByGroupSimulationId(request)).thenReturn(Optional.of(LstSimulationModel));
        Mockito.when(debtsModelService.findDebtsByDocument(request)).thenReturn(Optional.of(lstDebts));
        Mockito.when(simulationFactory.convertListSimulationModelToSimulationResponse(LstSimulationModel,
                lstDebts)).thenReturn(simulationResponse);
        assertNotNull(simulationServiceImpl.findByGroupSimulationId(request));
    }

    @Test
    void testGenerate() throws Exception {
        List<DebtModel> lstDebts = DebtMock.lstDebts();
        SimulationRequestDTO request = SimulationMock.getSimulationRequest();
        List<SimulationModel> LstSimulationModel = SimulationMock.getLstSimulationModel();
        SimulationResponseDTO simulationResponse = SimulationMock.getSimulationResponse();
        Mockito.when(debtsModelService.findDebtsByDocument(request)).thenReturn(Optional.of(lstDebts));
        Mockito.when(simulationModelService.generate(request, lstDebts)).thenReturn(LstSimulationModel);
        assertNotNull(simulationServiceImpl.generate(request));
    }
}
