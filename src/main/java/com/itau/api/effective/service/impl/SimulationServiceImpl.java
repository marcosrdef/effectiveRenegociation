package com.itau.api.effective.service.impl;

import com.itau.api.effective.constants.Constants;
import com.itau.api.effective.dto.SimulationRequestDTO;
import com.itau.api.effective.dto.SimulationResponseDTO;
import com.itau.api.effective.enums.TypeSearchEnum;
import com.itau.api.effective.exception.NotFoundException;
import com.itau.api.effective.factory.SimulationFactory;
import com.itau.api.effective.model.DebtModel;
import com.itau.api.effective.model.SimulationModel;
import com.itau.api.effective.service.DebtsModelService;
import com.itau.api.effective.service.SimulationModelService;
import com.itau.api.effective.service.SimulationService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class SimulationServiceImpl implements SimulationService {

    private final SimulationModelService simulationModelService;
    private final DebtsModelService debtsModelService;
    private final SimulationFactory simulationFactory;

    public SimulationServiceImpl(final SimulationModelService simulationModelService,
                                 final DebtsModelService debtsModelService,
                                 final SimulationFactory simulationFactory) {
        this.simulationModelService = simulationModelService;
        this.debtsModelService = debtsModelService;
        this.simulationFactory = simulationFactory;
    }

    @Override
    public SimulationResponseDTO findByDocumentId(SimulationRequestDTO simulateRequest) {
        List<DebtModel> debts = null;
        return simulationFactory.convertListSimulationModelToSimulationResponse(
                findItensSimulation(simulateRequest, TypeSearchEnum.ID, debts), debts);
    }

    @Override
    public SimulationResponseDTO findByGroupSimulationId(SimulationRequestDTO simulateRequest) {
        List<DebtModel> debts = null;
        return simulationFactory.convertListSimulationModelToSimulationResponse(
                findItensSimulation(simulateRequest, TypeSearchEnum.GROUP_SIMULATION_ID, debts), debts);
    }

    @Override
    public List<SimulationModel> generate(SimulationRequestDTO simulateRequest) throws Exception {
        return simulate(simulateRequest);
    }

    private List<SimulationModel> findItensSimulation(SimulationRequestDTO simulateRequest
            ,TypeSearchEnum typeSearchEnum, List<DebtModel> debts) {
        Optional<List<SimulationModel>> lstSimulation = typeSearchEnum.ID.equals(typeSearchEnum) ?
                this.simulationModelService.findByDocumentId(simulateRequest) :
                this.simulationModelService.findByGroupSimulationId(simulateRequest);;
        if (lstSimulation.isEmpty()) {
            throw new NotFoundException(String.format(Constants.MSG_NOT_FOUND_SIMULATION));
        }

        simulateRequest.setDocumentId(ObjectUtils.isEmpty(simulateRequest.getDocumentId())?
                lstSimulation.get().stream().findFirst().get().getDocumentId(): simulateRequest.getDocumentId());
        debts = this.debtsModelService.findDebtsByDocument(simulateRequest).get();

        return lstSimulation.get();
    }

    private List<SimulationModel> simulate(SimulationRequestDTO simulateRequest) throws Exception {
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
