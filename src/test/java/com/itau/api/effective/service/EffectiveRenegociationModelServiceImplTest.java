package com.itau.api.effective.service;

import com.itau.api.effective.dto.EffectiveRequestDTO;
import com.itau.api.effective.mock.EffectiveMock;
import com.itau.api.effective.model.EffectiveRenegociationModel;
import com.itau.api.effective.repository.EffectiveRenegociationModelRepository;
import com.itau.api.effective.service.impl.EffectiveRenegociationModelServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class EffectiveRenegociationModelServiceImplTest {

    @Mock
    private EffectiveRenegociationModelRepository effectiveRenegociationModelRepository;

    @InjectMocks
    private EffectiveRenegociationModelServiceImpl effectiveRenegociationModelServiceImpl;

    @Test
    void testInsert() {
        EffectiveRequestDTO request = EffectiveMock.getEffectiveRequest();
        EffectiveRenegociationModel effectiveModel = EffectiveMock.getEffectiveModel();
        Mockito.when(effectiveRenegociationModelRepository.save(Mockito.any())).thenReturn(effectiveModel);
        assertNotNull(effectiveRenegociationModelServiceImpl.insert(request));
    }

    @Test
    void testUpdate() {
        EffectiveRequestDTO request = EffectiveMock.getEffectiveRequest();
        EffectiveRenegociationModel effectiveModel = EffectiveMock.getEffectiveModel();
        Mockito.when(effectiveRenegociationModelRepository.save(Mockito.any())).thenReturn(effectiveModel);
        assertNotNull(effectiveRenegociationModelServiceImpl.update(effectiveModel));
    }
}
