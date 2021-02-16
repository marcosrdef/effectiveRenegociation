package com.itau.api.effective.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.effective.dto.EffectiveRequestDTO;

public interface EffectiveRenegociationFactory {
    EffectiveRequestDTO convertStringToEffectiveRequest(String message) throws JsonProcessingException;
}
