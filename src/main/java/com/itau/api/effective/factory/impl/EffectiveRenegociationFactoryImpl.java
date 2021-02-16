package com.itau.api.effective.factory.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.api.effective.dto.EffectiveRequestDTO;
import com.itau.api.effective.dto.SimulateRequestDTO;
import com.itau.api.effective.factory.EffectiveRenegociationFactory;
import org.springframework.stereotype.Component;

@Component
public class EffectiveRenegociationFactoryImpl implements EffectiveRenegociationFactory {
    @Override
    public EffectiveRequestDTO convertStringToEffectiveRequest(String message) throws JsonProcessingException {
        return new ObjectMapper().readValue(message, EffectiveRequestDTO.class);
    }
}
