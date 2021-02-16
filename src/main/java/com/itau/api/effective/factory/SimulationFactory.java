package com.itau.api.effective.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itau.api.effective.dto.SimulationRequestDTO;
import com.itau.api.effective.dto.SimulationResponseDTO;
import com.itau.api.effective.model.DebtModel;
import com.itau.api.effective.model.SimulationModel;

import java.util.List;

public interface SimulationFactory {
    SimulationResponseDTO convertListSimulationModelToSimulationResponse(List<SimulationModel> lstSimulationModel, List<DebtModel> lstDebtModel);
    SimulationRequestDTO convertStringToSimulateRequest(String message) throws JsonProcessingException;
}
