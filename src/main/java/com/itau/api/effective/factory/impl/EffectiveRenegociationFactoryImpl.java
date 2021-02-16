package com.itau.api.effective.factory.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.api.effective.dto.DebtDTO;
import com.itau.api.effective.dto.EffectiveRequestDTO;
import com.itau.api.effective.dto.EffectiveResponseDTO;
import com.itau.api.effective.factory.EffectiveRenegociationFactory;
import com.itau.api.effective.model.DebtModel;
import com.itau.api.effective.model.EffectiveRenegociationModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EffectiveRenegociationFactoryImpl implements EffectiveRenegociationFactory {
    @Override
    public EffectiveResponseDTO convertEffectiveModelToEffectiveResponse(EffectiveRenegociationModel effectiveRenegociationModel, List<DebtModel> lstDebtModel) {
        List<DebtDTO> debts = new ArrayList<>();
        lstDebtModel.forEach(debtItem -> {
            debts.add(DebtDTO.builder()
                    .currentValue(debtItem.getCurrentValue())
                    .originalValue(debtItem.getDebitValueOriginal())
                    .id(debtItem.getId())
                    .currency(effectiveRenegociationModel.getCurrency())
                    .build());
        });
        return EffectiveResponseDTO.builder()
                .effectiveValue(effectiveRenegociationModel.getEffectiveValue())
                .currency(effectiveRenegociationModel.getCurrency())
                .date(effectiveRenegociationModel.getDate())
                .debts(debts)
                .discountedValue(effectiveRenegociationModel.getDiscountedValue())
                .id(effectiveRenegociationModel.getId())
                .documentId(effectiveRenegociationModel.getDocumentId())
                .discountPercentage(effectiveRenegociationModel.getDiscountPercentage())
                .installmentValue(effectiveRenegociationModel.getInstallmentValue())
                .originalValue(effectiveRenegociationModel.getOriginalValue())
                .plots(effectiveRenegociationModel.getPlots())
                .simulationId(effectiveRenegociationModel.getSimulationId())
                .status(effectiveRenegociationModel.getStatus())
                .transactionId(effectiveRenegociationModel.getTransactionId())
                .build();
    }

    @Override
    public EffectiveRequestDTO convertStringToEffectiveRequest(String message) throws JsonProcessingException {
        return new ObjectMapper().readValue(message, EffectiveRequestDTO.class);
    }
}
