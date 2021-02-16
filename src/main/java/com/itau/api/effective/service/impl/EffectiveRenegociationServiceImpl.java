package com.itau.api.effective.service.impl;

import com.itau.api.effective.constants.Constants;
import com.itau.api.effective.dto.EffectiveRequestDTO;
import com.itau.api.effective.dto.EffectiveResponseDTO;
import com.itau.api.effective.dto.SimulationRequestDTO;
import com.itau.api.effective.exception.NotFoundException;
import com.itau.api.effective.factory.EffectiveRenegociationFactory;
import com.itau.api.effective.model.DebtModel;
import com.itau.api.effective.model.EffectiveRenegociationModel;
import com.itau.api.effective.model.SimulationModel;
import com.itau.api.effective.service.DebtsModelService;
import com.itau.api.effective.service.EffectiveRenegociationModelService;
import com.itau.api.effective.service.EffectiveRenegociationService;
import com.itau.api.effective.service.SimulationModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EffectiveRenegociationServiceImpl implements EffectiveRenegociationService {

    private final EffectiveRenegociationModelService effectiveRenegociationModelService;
    private final EffectiveRenegociationFactory effectiveRenegociationFactory;
    private final SimulationModelService simulationModelService;
    private final DebtsModelService debtsModelService;


    public EffectiveRenegociationServiceImpl(final EffectiveRenegociationModelService effectiveRenegociationModelService,
                                             final SimulationModelService simulationModelService,
                                             final EffectiveRenegociationFactory effectiveRenegociationFactory,
                                             final DebtsModelService debtsModelService) {
        this.effectiveRenegociationModelService = effectiveRenegociationModelService;
        this.simulationModelService = simulationModelService;
        this.effectiveRenegociationFactory = effectiveRenegociationFactory;
        this.debtsModelService = debtsModelService;
    }

    @Override
    public EffectiveRenegociationModel effective(EffectiveRequestDTO effectiveRequest) {
        return this.processRenegociation(
                effectiveRequest,
                initRenegociation(effectiveRequest));
    }

    @Override
    public List<EffectiveRenegociationModel> findByDocumentId(EffectiveRequestDTO effectiveRequest) {
        return validatedEffectiveList(effectiveRequest);
    }

    @Override
    public EffectiveResponseDTO findByTransactionId(EffectiveRequestDTO effectiveRequest) {
        EffectiveRenegociationModel effectiveRenegociationModel = validatedEffectiveItem(effectiveRequest);
        getDebts(effectiveRenegociationModel);
        return effectiveRenegociationFactory.convertEffectiveModelToEffectiveResponse(effectiveRenegociationModel,
                getDebts(effectiveRenegociationModel));
    }

    private EffectiveRenegociationModel processRenegociation(EffectiveRequestDTO effectiveRequest, EffectiveRenegociationModel effectiveRenegociationModel) {
        Optional<SimulationModel> simulationModel = this.simulationModelService.findById(effectiveRequest.getSimulationId());
        if (simulationModel.isEmpty()) {
            throw new NotFoundException(String.format(Constants.MSG_NOT_FOUND_SIMULATION));
        }
        return effectiveRenegociation(
                    updateRenegociation(effectiveRenegociationModel, simulationModel.get()));
    }

    private EffectiveRenegociationModel initRenegociation(EffectiveRequestDTO effectiveRequest) {
        return this.effectiveRenegociationModelService.insert(effectiveRequest);
    }

    private EffectiveRenegociationModel updateRenegociation(EffectiveRenegociationModel effectiveRenegociationModel
            ,SimulationModel simulationModel) {
        return EffectiveRenegociationModel
                .builder()
                .id(effectiveRenegociationModel.getId())
                .transactionId(effectiveRenegociationModel.getTransactionId())
                .date(effectiveRenegociationModel.getDate())
                .documentId(effectiveRenegociationModel.getDocumentId())
                .effectiveValue(simulationModel.getValueWithDiscount())
                .discountPercentage(simulationModel.getDiscountPercent())
                .plots(simulationModel.getPlots())
                .installmentValue(simulationModel.getInstallmentValue())
                .originalValue(simulationModel.getOriginalValue())
                .discountedValue(simulationModel.getDiscountedValue())
                .simulationId(simulationModel.getId())
                .currency(Constants.CURRENCY)
                .status(Constants.STATUS_EFETIVADO)
                .build();
    }
    private EffectiveRenegociationModel effectiveRenegociation(EffectiveRenegociationModel effectiveRenegociationModel) {
        return this.effectiveRenegociationModelService.update(effectiveRenegociationModel);
    }

    private List<EffectiveRenegociationModel> validatedEffectiveList(EffectiveRequestDTO effectiveRequest) {
        Optional<List<EffectiveRenegociationModel>> lstEffectives = this.effectiveRenegociationModelService.findByDocumentId(effectiveRequest.getDocumentId());
        if (lstEffectives.isEmpty()) {
            throw new NotFoundException(String.format(Constants.MSG_NOT_FOUND_RENEGOCIATIONS, effectiveRequest.getDocumentId() ));
        }
        return lstEffectives.get();
    }

    private EffectiveRenegociationModel validatedEffectiveItem(EffectiveRequestDTO effectiveRequest) {
        Optional<EffectiveRenegociationModel> effectiveItem = this.effectiveRenegociationModelService.findByTransactionId(effectiveRequest.getTransactionId());
        if (effectiveItem.isEmpty()) {
            throw new NotFoundException(String.format(Constants.MSG_NOT_FOUND_RENEGOCIATIONS, effectiveRequest.getDocumentId()));
        }
        return effectiveItem.get();
    }

    private List<DebtModel> getDebts(EffectiveRenegociationModel effectiveRenegociationModel) {
        Optional<SimulationModel> simulationModel = this.simulationModelService.findById(effectiveRenegociationModel.getSimulationId());
        if (simulationModel.isEmpty()) {
            throw new NotFoundException(String.format(Constants.MSG_NOT_FOUND_SIMULATION));
        }

        Optional<List<DebtModel>> lstDebts = this.debtsModelService.findDebtsByDocument(
                SimulationRequestDTO.builder()
                .documentId(simulationModel.get().getDocumentId()).build());
        if (lstDebts.isEmpty()) {
            throw new NotFoundException(String.format(Constants.MSG_NOT_FOUND_DEBTS));
        }
        List<DebtModel> lstDebtsFiltered = lstDebts.get().stream()
                .filter(x-> simulationModel.get().getIdDebts().equals(x.getId())).collect(Collectors.toList());
        if (lstDebtsFiltered.size() == 0) {
            throw new NotFoundException(String.format(Constants.MSG_NOT_FOUND_DEBTS));
        }
        return lstDebtsFiltered;

    }

}
