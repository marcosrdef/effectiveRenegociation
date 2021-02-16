package com.itau.api.effective.service.impl;

import com.itau.api.effective.constants.Constants;
import com.itau.api.effective.dto.SimulateRequestDTO;
import com.itau.api.effective.enums.TypeSearchEnum;
import com.itau.api.effective.exception.NotFoundException;
import com.itau.api.effective.model.DebtModel;
import com.itau.api.effective.model.SimulationModel;
import com.itau.api.effective.service.DebtsModelService;
import com.itau.api.effective.service.SimulationModelService;
import com.itau.api.effective.service.SimulationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimulationServiceImpl implements SimulationService {

    private final SimulationModelService simulationModelService;
    private final DebtsModelService debtsModelService;

    public SimulationServiceImpl(final SimulationModelService simulationModelService,
                                 final DebtsModelService debtsModelService) {
        this.simulationModelService = simulationModelService;
        this.debtsModelService = debtsModelService;
    }

    @Override
    public List<SimulationModel> findByDocumentId(SimulateRequestDTO simulateRequest) {
        return findItensSimulation(simulateRequest, TypeSearchEnum.ID);
    }

    @Override
    public List<SimulationModel> findByGroupSimulationId(SimulateRequestDTO simulateRequest) {
        return findItensSimulation(simulateRequest, TypeSearchEnum.GROUP_SIMULATION_ID);
    }

    @Override
    public List<SimulationModel> generate(SimulateRequestDTO simulateRequest) throws Exception {
        return simulate(simulateRequest);
    }

    private List<SimulationModel> findItensSimulation(SimulateRequestDTO simulateRequest, TypeSearchEnum typeSearchEnum) {
        Optional<List<SimulationModel>> lstSimulation = typeSearchEnum.ID.equals(typeSearchEnum) ?
                this.simulationModelService.findByDocumentId(simulateRequest) :
                this.simulationModelService.findByGroupSimulationId(simulateRequest);;
        if (lstSimulation.isEmpty()) {
            throw new NotFoundException(String.format(Constants.MSG_NOT_FOUND_SIMULATION));
        }
        return lstSimulation.get();
    }

    private List<SimulationModel> simulate(SimulateRequestDTO simulateRequest) throws Exception {
        Optional<List<DebtModel>> lstDebts = this.debtsModelService.findDebtsByDocument(simulateRequest);
        if (lstDebts.isEmpty()) {
            throw new NotFoundException(String.format(Constants.MSG_NOT_FOUND_DEBTS, simulateRequest.getDocumentId()));
        }
        Optional<List<SimulationModel>> lstSimulation = Optional.of(this.simulationModelService.generate(simulateRequest,lstDebts.get()));
        if (lstSimulation.isEmpty()) {
            throw new Exception(String.format(Constants.MSG_ERROR_SIMULATION, simulateRequest.getDocumentId()));
        }
        return lstSimulation.get();
    }

}
