package com.itau.api.effective.service;

import com.itau.api.effective.dto.SimulateRequestDTO;
import com.itau.api.effective.model.EffectiveRenegociationModel;
import com.itau.api.effective.model.SimulationModel;

import java.util.List;

public interface SimulationService {
    List<SimulationModel> findByDocumentId(SimulateRequestDTO simulateRequest);
    List<SimulationModel> findByGroupSimulationId(SimulateRequestDTO simulateRequest);
    List<SimulationModel> generate(SimulateRequestDTO simulateRequest) throws Exception;
}
