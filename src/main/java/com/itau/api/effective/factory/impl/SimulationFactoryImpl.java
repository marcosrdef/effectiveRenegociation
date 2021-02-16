package com.itau.api.effective.factory.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.api.effective.constants.Constants;
import com.itau.api.effective.dto.DebtDTO;
import com.itau.api.effective.dto.SimulationItemDTO;
import com.itau.api.effective.dto.SimulationRequestDTO;
import com.itau.api.effective.dto.SimulationResponseDTO;
import com.itau.api.effective.factory.SimulationFactory;
import com.itau.api.effective.model.DebtModel;
import com.itau.api.effective.model.SimulationModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SimulationFactoryImpl implements SimulationFactory {
    @Override
    public SimulationResponseDTO convertListSimulationModelToSimulationResponse(List<SimulationModel> lstSimulationModel, List<DebtModel> lstDebtModel) {
        List<SimulationItemDTO> simulations = new ArrayList<>();
        lstSimulationModel.forEach(itemSimulationModel->{
            List<DebtDTO> lstDebts = new ArrayList<>();
            itemSimulationModel.getIdDebts().forEach(debtItem->{
                Optional<DebtModel> debtModel = lstDebtModel.stream().filter(x-> debtItem.equals(x.getId())).findFirst();
                if (!debtModel.isEmpty()) {
                    lstDebts.add(DebtDTO.builder()
                            .id(debtModel.get().getId())
                            .originalValue(debtModel.get().getDebitValueOriginal())
                            .currentValue(debtModel.get().getCurrentValue())
                            .currency(Constants.CURRENCY)
                            .build());
                }

            });
            simulations.add(SimulationItemDTO.builder()
                    .groupSimulationId(itemSimulationModel.getGroupSimulationId())
                    .date(itemSimulationModel.getDate())
                    .discountPercent(itemSimulationModel.getDiscountPercent())
                    .documentId(itemSimulationModel.getDocumentId())
                    .idDebts(lstDebts)
                    .discountedValue(itemSimulationModel.getDiscountedValue())
                    .installmentValue(itemSimulationModel.getInstallmentValue())
                    .status(itemSimulationModel.getStatus())
                    .plots(itemSimulationModel.getPlots())
                    .valueWithDiscount(itemSimulationModel.getValueWithDiscount())
                    .currency(itemSimulationModel.getCurrency())
                    .id(itemSimulationModel.getId())
                    .originalValue(itemSimulationModel.getOriginalValue())
                    .build());

        });
        return SimulationResponseDTO
                .builder()
                .simulations(simulations)
                .build();
    }

    @Override
    public SimulationRequestDTO convertStringToSimulateRequest(String message) throws JsonProcessingException {
        return new ObjectMapper().readValue(message, SimulationRequestDTO.class);
    }
}
