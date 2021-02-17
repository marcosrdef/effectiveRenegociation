package com.itau.api.effective.service;

import com.itau.api.effective.dto.EffectiveRequestDTO;
import com.itau.api.effective.dto.SimulationRequestDTO;
import com.itau.api.effective.mock.DebtMock;
import com.itau.api.effective.mock.EffectiveMock;
import com.itau.api.effective.mock.SimulationMock;
import com.itau.api.effective.repository.DebtsModelRepository;
import com.itau.api.effective.service.impl.DebtsModelServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class DebtsModelServiceImplTest {

    @InjectMocks
    private DebtsModelServiceImpl DebtsModelServiceImpl;

    @Mock
    private DebtsModelRepository debtsModelRepository;

    @Test
    void testFindDebtsByDocument() {
        SimulationRequestDTO request = SimulationMock.getSimulationRequest();
        Mockito.when(debtsModelRepository.findByDocumentId(request.getDocumentId())).thenReturn(Optional.of(DebtMock.lstDebts()));
        assertNotNull(DebtsModelServiceImpl.findDebtsByDocument(request));
    }
}
