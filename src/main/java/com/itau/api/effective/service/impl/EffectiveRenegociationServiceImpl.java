package com.itau.api.effective.service.impl;

import com.itau.api.effective.constants.Constants;
import com.itau.api.effective.dto.EffectiveRequestDTO;
import com.itau.api.effective.exception.NotFoundException;
import com.itau.api.effective.model.EffectiveRenegociationModel;
import com.itau.api.effective.model.SimulationModel;
import com.itau.api.effective.service.EffectiveRenegociationModelService;
import com.itau.api.effective.service.EffectiveRenegociationService;
import com.itau.api.effective.service.SimulationModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EffectiveRenegociationServiceImpl implements EffectiveRenegociationService {

    private final EffectiveRenegociationModelService effectiveRenegociationModelService;
    private final SimulationModelService simulationModelService;

    public EffectiveRenegociationServiceImpl(final EffectiveRenegociationModelService effectiveRenegociationModelService,
                                             final SimulationModelService simulationModelService) {
        this.effectiveRenegociationModelService = effectiveRenegociationModelService;
        this.simulationModelService = simulationModelService;
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
    public EffectiveRenegociationModel findByTransactionId(EffectiveRequestDTO effectiveRequest) {
        return validatedEffectiveItem(effectiveRequest);
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

}
