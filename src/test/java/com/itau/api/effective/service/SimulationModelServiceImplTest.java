package com.itau.api.effective.service;

import com.itau.api.effective.dto.EffectiveRequestDTO;
import com.itau.api.effective.dto.SimulationRequestDTO;
import com.itau.api.effective.mock.DebtMock;
import com.itau.api.effective.mock.EffectiveMock;
import com.itau.api.effective.mock.SimulationMock;
import com.itau.api.effective.model.DebtModel;
import com.itau.api.effective.model.EffectiveRenegociationModel;
import com.itau.api.effective.model.SimulationModel;
import com.itau.api.effective.repository.SimulationModelRepository;
import com.itau.api.effective.service.impl.SimulationModelServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class SimulationModelServiceImplTest {

    @InjectMocks
    private SimulationModelServiceImpl simulationModelServiceImpl;

    @Mock
    private SimulationModelRepository simulationModelRepository;

    @Test
    void testFindByDocumentId() {
        SimulationRequestDTO request = SimulationMock.getSimulationRequest();
        List<SimulationModel> LstSimulationModel = SimulationMock.getLstSimulationModel();
        Mockito.when(simulationModelRepository.findByDocumentId(request.getDocumentId())).thenReturn(Optional.of(LstSimulationModel));
        Mockito.when(simulationModelRepository.findByDocumentId(request.getDocumentId())).thenReturn(Optional.of(LstSimulationModel));
        assertNotNull(simulationModelServiceImpl.findByDocumentId(request));
    }

    @Test
    void testFindByGroupSimulationId() {
        SimulationRequestDTO request = SimulationMock.getSimulationRequest();
        List<SimulationModel> LstSimulationModel = SimulationMock.getLstSimulationModel();
        Mockito.when(simulationModelRepository.findByGroupSimulationId(request.getGroupSimulationId())).thenReturn(Optional.of(LstSimulationModel));
        assertNotNull(simulationModelServiceImpl.findByGroupSimulationId(request));
    }

    @Test
    void testFindById() {
        SimulationRequestDTO request = SimulationMock.getSimulationRequest();
        SimulationModel simulationModel = SimulationMock.getSimulationItemModel();
        Mockito.when(simulationModelRepository.findById(request.getSimulateId())).thenReturn(Optional.of(simulationModel));
        assertNotNull(simulationModelServiceImpl.findById(request.getSimulateId()));
    }

    @Test
    void testGenerate() throws ParseException {
        SimulationRequestDTO request = SimulationMock.getSimulationRequest();
        List<DebtModel> lstDebts = DebtMock.lstDebts();
        assertNotNull(simulationModelServiceImpl.generate(request, lstDebts));
    }

}
