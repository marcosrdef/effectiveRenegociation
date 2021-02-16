package com.itau.api.effective.service;

import com.itau.api.effective.dto.EffectiveRequestDTO;
import com.itau.api.effective.dto.EffectiveResponseDTO;
import com.itau.api.effective.model.EffectiveRenegociationModel;

import java.util.List;
import java.util.Optional;

public interface EffectiveRenegociationService {
    EffectiveRenegociationModel effective(EffectiveRequestDTO effectiveRequest);
    List<EffectiveRenegociationModel> findByDocumentId(EffectiveRequestDTO effectiveRequest);
    EffectiveResponseDTO findByTransactionId(EffectiveRequestDTO effectiveRequest);
}
