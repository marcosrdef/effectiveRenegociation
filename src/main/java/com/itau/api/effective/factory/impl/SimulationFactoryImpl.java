package com.itau.api.effective.factory.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.api.effective.dto.SimulateRequestDTO;
import com.itau.api.effective.factory.SimulationFactory;
import org.springframework.stereotype.Component;

@Component
public class SimulationFactoryImpl implements SimulationFactory {
    @Override
    public SimulateRequestDTO convertStringToSimulateRequest(String message) throws JsonProcessingException {
        return new ObjectMapper().readValue(message, SimulateRequestDTO.class);
    }
}
