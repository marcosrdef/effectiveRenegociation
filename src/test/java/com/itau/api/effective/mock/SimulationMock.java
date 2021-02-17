package com.itau.api.effective.mock;

import com.itau.api.effective.dto.DebtDTO;
import com.itau.api.effective.dto.SimulationItemDTO;
import com.itau.api.effective.dto.SimulationRequestDTO;
import com.itau.api.effective.dto.SimulationResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class SimulationMock {

    public static SimulationRequestDTO getSimulationRequest() {
        return SimulationRequestDTO.builder()
                .groupSimulationId("22b9bc27-e6ed-4466-848e-3e8a788b2ab2")
                .build();
    }

    public static SimulationResponseDTO getSimulationResponse() {
        List<SimulationItemDTO> simulations = new ArrayList<>();
        List<DebtDTO> debts = new ArrayList<>();
        debts.add(DebtDTO.builder()
                    .id("5165b780-ec4f-4ea8-9c17-2ebaa2d7affd")
                    .currentValue("16000,00")
                    .originalValue("10000,00")
                    .currency("R$")
                    .build());
        simulations.add(SimulationItemDTO.builder()
                        .id("31763879-97d7-4160-a172-667beaa376fa")
                        .date("2021-02-16T23:55:13.585Z")
                        .originalValue("16000,00")
                        .valueWithDiscount("14400,00")
                        .discountedValue("1600,00")
                        .discountPercent("10")
                        .plots("10")
                        .installmentValue("1440,00")
                        .documentId("12768334073")
                        .idDebts(debts)
                        .status("Efetivado")
                        .groupSimulationId("22b9bc27-e6ed-4466-848e-3e8a788b2ab2")
                        .currency("R$")
                        .build());
        return SimulationResponseDTO.builder()
                .simulations(simulations)
                .build();
    }

}
