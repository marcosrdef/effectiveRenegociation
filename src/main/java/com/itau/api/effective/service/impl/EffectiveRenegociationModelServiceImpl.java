package com.itau.api.effective.service.impl;

import com.itau.api.effective.constants.Constants;
import com.itau.api.effective.dto.EffectiveRequestDTO;
import com.itau.api.effective.model.EffectiveRenegociationModel;
import com.itau.api.effective.repository.EffectiveRenegociationModelRepository;
import com.itau.api.effective.service.EffectiveRenegociationModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EffectiveRenegociationModelServiceImpl implements EffectiveRenegociationModelService {

    private final EffectiveRenegociationModelRepository effectiveRenegociationModelRepository;

    public EffectiveRenegociationModelServiceImpl(final EffectiveRenegociationModelRepository effectiveRenegociationModelRepository) {
        this.effectiveRenegociationModelRepository = effectiveRenegociationModelRepository;
    }

    @Override
    public EffectiveRenegociationModel insert(EffectiveRequestDTO effectiveRequest) {
        EffectiveRenegociationModel effectiveRenegociationModel =
                EffectiveRenegociationModel.builder()
                .documentId(effectiveRequest.getDocumentId())
                .transactionId(effectiveRequest.getTransactionId())
                .status(Constants.STATUS_PROCESSANDO)
                .currency(Constants.CURRENCY)
                .build();
        return this.effectiveRenegociationModelRepository.save(effectiveRenegociationModel);
    }

    @Override
    public EffectiveRenegociationModel update(EffectiveRenegociationModel effectiveRenegociationModel) {
        return this.effectiveRenegociationModelRepository.save(effectiveRenegociationModel);
    }

    @Override
    public Optional<List<EffectiveRenegociationModel>> findByDocumentId(String documentId) {
        return this.effectiveRenegociationModelRepository.findByDocumentId(documentId);
    }

    @Override
    public Optional<EffectiveRenegociationModel> findByTransactionId(String transactionId) {
        return this.effectiveRenegociationModelRepository.findByTransactionId(transactionId);
    }
}
