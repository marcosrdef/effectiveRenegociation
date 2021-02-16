package com.itau.api.effective.service;

import com.itau.api.effective.dto.SimulationRequestDTO;
import com.itau.api.effective.dto.SimulationResponseDTO;
import com.itau.api.effective.model.SimulationModel;

import java.util.List;

public interface SimulationService {
    SimulationResponseDTO findByDocumentId(SimulationRequestDTO simulateRequest);
    SimulationResponseDTO findByGroupSimulationId(SimulationRequestDTO simulateRequest);
    List<SimulationModel> generate(SimulationRequestDTO simulateRequest) throws Exception;
}
