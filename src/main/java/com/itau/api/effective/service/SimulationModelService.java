package com.itau.api.effective.service;

import com.itau.api.effective.dto.SimulateRequestDTO;
import com.itau.api.effective.model.DebtModel;
import com.itau.api.effective.model.EffectiveRenegociationModel;
import com.itau.api.effective.model.SimulationModel;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface SimulationModelService {
    Optional<List<SimulationModel>> findByDocumentId(SimulateRequestDTO simulateRequest);
    Optional<List<SimulationModel>> findByGroupSimulationId(SimulateRequestDTO simulateRequest);
    List<SimulationModel> generate(SimulateRequestDTO simulateRequest, List<DebtModel> lstModel) throws ParseException;
    Optional<SimulationModel> findById(String id);
}
