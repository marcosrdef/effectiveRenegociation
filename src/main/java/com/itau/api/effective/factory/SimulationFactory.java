package com.itau.api.effective.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.effective.dto.SimulateRequestDTO;

public interface SimulationFactory {
    SimulateRequestDTO convertStringToSimulateRequest(String message) throws JsonProcessingException;
}
