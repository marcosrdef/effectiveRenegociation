package com.itau.api.effective.service;

import com.itau.api.effective.dto.EffectiveRequestDTO;
import com.itau.api.effective.model.EffectiveRenegociationModel;

import java.util.List;
import java.util.Optional;

public interface EffectiveRenegociationModelService {
    EffectiveRenegociationModel insert(EffectiveRequestDTO effectiveRequest);
    EffectiveRenegociationModel update(EffectiveRenegociationModel effectiveRenegociationModel);
    Optional<List<EffectiveRenegociationModel>> findByDocumentId(String documentId);
    Optional<EffectiveRenegociationModel> findByTransactionId(String findByTransactionId);
}
