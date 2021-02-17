package com.itau.api.effective.service;

import com.itau.api.effective.dto.EffectiveRequestDTO;
import com.itau.api.effective.dto.EffectiveResponseDTO;
import com.itau.api.effective.dto.SimulationRequestDTO;
import com.itau.api.effective.exception.NotFoundException;
import com.itau.api.effective.factory.EffectiveRenegociationFactory;
import com.itau.api.effective.mock.DebtMock;
import com.itau.api.effective.mock.EffectiveMock;
import com.itau.api.effective.mock.SimulationMock;
import com.itau.api.effective.model.DebtModel;
import com.itau.api.effective.model.EffectiveRenegociationModel;
import com.itau.api.effective.model.SimulationModel;
import com.itau.api.effective.service.impl.EffectiveRenegociationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class EffectiveRenegociationServiceImplTest {

    @InjectMocks
    private EffectiveRenegociationServiceImpl effectiveRenegociationServiceImpl;

    @Mock
    private EffectiveRenegociationModelService effectiveRenegociationModelService;

    @Mock
    private EffectiveRenegociationFactory effectiveRenegociationFactory;

    @Mock
    private SimulationModelService simulationModelService;

    @Mock
    private DebtsModelService debtsModelService;

    @Test
    void testEffective() {
        EffectiveRequestDTO request = EffectiveMock.getEffectiveRequest();
        EffectiveRenegociationModel effectiveModel = EffectiveMock.getEffectiveModel();
        Mockito.when(effectiveRenegociationModelService.insert(request)).thenReturn(effectiveModel);
        Mockito.when(simulationModelService.findById(request.getSimulationId())).thenReturn(Optional.of(SimulationMock.getSimulationItemModel()));
        Mockito.when(effectiveRenegociationModelService.update(Mockito.any())).thenReturn(effectiveModel);
        assertNotNull(effectiveRenegociationServiceImpl.effective(request));
    }

    @Test
    void testEffectiveNotFound() {
        EffectiveRequestDTO request = EffectiveMock.getEffectiveRequest();
        EffectiveRenegociationModel effectiveModel = EffectiveMock.getEffectiveModel();
        Mockito.when(effectiveRenegociationModelService.insert(request)).thenReturn(effectiveModel);
        Mockito.when(simulationModelService.findById(request.getSimulationId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{
            effectiveRenegociationServiceImpl.effective(request);
        });

    }

    @Test
    void testFindByDocumentId() {
        EffectiveRequestDTO request = EffectiveMock.getEffectiveRequest();
        List<EffectiveRenegociationModel> LstEffectiveModel = EffectiveMock.getLstEffectiveModel();
        Mockito.when(effectiveRenegociationModelService.findByDocumentId(request.getDocumentId())).thenReturn(Optional.of(LstEffectiveModel));
        assertNotNull(effectiveRenegociationServiceImpl.findByDocumentId(request));
    }

    @Test
    void testFindByDocumentIdNotFound() {
        EffectiveRequestDTO request = EffectiveMock.getEffectiveRequest();
        List<EffectiveRenegociationModel> LstEffectiveModel = EffectiveMock.getLstEffectiveModel();
        assertThrows(NotFoundException.class, () ->{
            effectiveRenegociationServiceImpl.findByDocumentId(request);
        });
    }

    @Test
    void testFindByTrasactionId() {
        EffectiveRequestDTO request = EffectiveMock.getEffectiveRequest();
        SimulationRequestDTO requestSimulation = SimulationMock.getSimulationRequest();
        EffectiveRenegociationModel effectiveModel = EffectiveMock.getEffectiveModel();
        SimulationModel simulationModel = SimulationMock.getSimulationItemModel();
        List<DebtModel> lstDebts = DebtMock.lstDebts();
        EffectiveResponseDTO effectiveResponse = EffectiveMock.getEffectiveResponse();
        Mockito.when(effectiveRenegociationModelService.findByTransactionId(request.getTransactionId())).thenReturn(Optional.of(effectiveModel));
        Mockito.when(simulationModelService.findById(Mockito.any())).thenReturn(Optional.of(simulationModel));
        Mockito.when(debtsModelService.findDebtsByDocument(Mockito.any())).thenReturn(Optional.of(lstDebts));
        Mockito.when(effectiveRenegociationFactory.convertEffectiveModelToEffectiveResponse(effectiveModel, lstDebts)).thenReturn(effectiveResponse);
        assertNotNull(effectiveRenegociationServiceImpl.findByTransactionId(request));
    }

    @Test
    void testFindByTrasactionIdNotFoundModelService() {
        EffectiveRequestDTO request = EffectiveMock.getEffectiveRequest();
        SimulationRequestDTO requestSimulation = SimulationMock.getSimulationRequest();
        EffectiveRenegociationModel effectiveModel = EffectiveMock.getEffectiveModel();
        SimulationModel simulationModel = SimulationMock.getSimulationItemModel();
        List<DebtModel> lstDebts = DebtMock.lstDebts();
        EffectiveResponseDTO effectiveResponse = EffectiveMock.getEffectiveResponse();
        Mockito.when(effectiveRenegociationModelService.findByTransactionId(request.getTransactionId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{
            effectiveRenegociationServiceImpl.findByTransactionId(request);
        });
    }

    @Test
    void testFindByTrasactionIdNotFoundSimulation() {
        EffectiveRequestDTO request = EffectiveMock.getEffectiveRequest();
        SimulationRequestDTO requestSimulation = SimulationMock.getSimulationRequest();
        EffectiveRenegociationModel effectiveModel = EffectiveMock.getEffectiveModel();
        SimulationModel simulationModel = SimulationMock.getSimulationItemModel();
        List<DebtModel> lstDebts = DebtMock.lstDebts();
        EffectiveResponseDTO effectiveResponse = EffectiveMock.getEffectiveResponse();
        Mockito.when(effectiveRenegociationModelService.findByTransactionId(request.getTransactionId())).thenReturn(Optional.of(effectiveModel));
        Mockito.when(simulationModelService.findById(Mockito.any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () ->{
            effectiveRenegociationServiceImpl.findByTransactionId(request);
        });
    }

}
