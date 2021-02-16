package com.itau.api.effective.service.impl;

import com.itau.api.effective.constants.Constants;
import com.itau.api.effective.dto.SimulationRequestDTO;
import com.itau.api.effective.model.DebtModel;
import com.itau.api.effective.model.SimulationModel;
import com.itau.api.effective.repository.SimulationModelRepository;
import com.itau.api.effective.service.SimulationModelService;
import com.itau.api.effective.utils.Utils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SimulationModelServiceImpl implements SimulationModelService {

    private final SimulationModelRepository simulationModelRepository;

    public SimulationModelServiceImpl(final SimulationModelRepository simulationModelRepository) {
        this.simulationModelRepository = simulationModelRepository;
    }
    @Override
    public Optional<List<SimulationModel>> findByDocumentId(SimulationRequestDTO simulateRequest) {
        return this.simulationModelRepository.findByDocumentId(simulateRequest.getDocumentId());
    }

    @Override
    public Optional<List<SimulationModel>> findByGroupSimulationId(SimulationRequestDTO simulateRequest) {
        return this.simulationModelRepository.findByGroupSimulationId(simulateRequest.getGroupSimulationId());
    }

    @Override
    public List<SimulationModel> generate(SimulationRequestDTO simulateRequest, List<DebtModel> lstModel) throws ParseException {
        return saveAll(simulateRequest,lstModel);
    }

    @Override
    public Optional<SimulationModel> findById(String id) {
        return this.simulationModelRepository.findById(id);
    }


    private List<SimulationModel> saveAll(SimulationRequestDTO simulateRequest, List<DebtModel> lstDebits) throws ParseException {
        List<SimulationModel> lstSimulationModel = new ArrayList<>();
        String valor = Utils.sumDebits(lstDebits);
        List<String> idDebits = lstDebits.stream().map(x -> x.getId()).collect(Collectors.toList());
        lstSimulationModel.add(simulate(simulateRequest, valor, Constants.TEN, Constants.TEN, idDebits));
        lstSimulationModel.add(simulate(simulateRequest, valor, Constants.FIVE, Constants.TWENTY_FIVE, idDebits));
        lstSimulationModel.add(simulate(simulateRequest, valor, Constants.TWO, Constants.FORTY_FIVE, idDebits));
        lstSimulationModel.add(simulate(simulateRequest, valor, Constants.ONE, Constants.SIXTY_FIVE, idDebits));

        return (List<SimulationModel>) this.simulationModelRepository.saveAll(lstSimulationModel);
    }

    private SimulationModel simulate(SimulationRequestDTO simulateRequest
            , String value
            , String plots
            , String percent
            , List<String> idDebits) throws ParseException {
        String discountValue = Utils.calculateValueWithDiscount(value, percent);
        return SimulationModel.builder()
                .groupSimulationId(simulateRequest.getGroupSimulationId())
                .documentId(simulateRequest.getDocumentId())
                .discountPercent(percent)
                .originalValue(value)
                .valueWithDiscount(discountValue)
                .plots(plots)
                .installmentValue(Utils.calculateinstallmentValue(discountValue, plots))
                .discountedValue(Utils.calculateDiscountedValue(value, discountValue))
                .idDebts(idDebits)
                .status(Constants.STATUS_EFETIVADO)
                .currency(Constants.CURRENCY)
                .build();
    }
}
