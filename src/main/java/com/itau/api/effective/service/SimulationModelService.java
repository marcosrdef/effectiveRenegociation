package com.itau.api.effective.service;

import com.itau.api.effective.dto.SimulationRequestDTO;
import com.itau.api.effective.model.DebtModel;
import com.itau.api.effective.model.SimulationModel;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface SimulationModelService {
    Optional<List<SimulationModel>> findByDocumentId(SimulationRequestDTO simulateRequest);
    Optional<List<SimulationModel>> findByGroupSimulationId(SimulationRequestDTO simulateRequest);
    List<SimulationModel> generate(SimulationRequestDTO simulateRequest, List<DebtModel> lstModel) throws ParseException;
    Optional<SimulationModel> findById(String id);
}
