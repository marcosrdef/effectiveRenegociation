package com.itau.api.effective.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.effective.dto.EffectiveRequestDTO;
import com.itau.api.effective.dto.EffectiveResponseDTO;
import com.itau.api.effective.model.DebtModel;
import com.itau.api.effective.model.EffectiveRenegociationModel;

import java.util.List;

public interface EffectiveRenegociationFactory {
    EffectiveResponseDTO convertEffectiveModelToEffectiveResponse(EffectiveRenegociationModel effectiveRenegociationModel, List<DebtModel> lstDebtModel);
    EffectiveRequestDTO convertStringToEffectiveRequest(String message) throws JsonProcessingException;
}
