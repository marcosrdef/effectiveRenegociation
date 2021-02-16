package com.itau.api.effective.service;

import com.itau.api.effective.dto.EffectiveRequestDTO;
import com.itau.api.effective.model.EffectiveRenegociationModel;

import java.util.List;
import java.util.Optional;

public interface EffectiveRenegociationService {
    EffectiveRenegociationModel effective(EffectiveRequestDTO effectiveRequest);
    List<EffectiveRenegociationModel> findByDocumentId(EffectiveRequestDTO effectiveRequest);
    EffectiveRenegociationModel findByTransactionId(EffectiveRequestDTO effectiveRequest);
}
