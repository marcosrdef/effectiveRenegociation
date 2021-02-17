package com.itau.api.effective.mock;

import com.itau.api.effective.dto.DebtDTO;
import com.itau.api.effective.dto.SimulationItemDTO;
import com.itau.api.effective.dto.SimulationRequestDTO;
import com.itau.api.effective.dto.SimulationResponseDTO;
import com.itau.api.effective.model.SimulationModel;

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

    public static String getMessageSimulation() {
        return "{\n" +
                "    \"groupSimulationId\": \"df46fa98-0a06-44c3-95ea-01a06f822ec6\",\n" +
                "    \"documentId\": \"89852935089\",\n" +
                "    \"date\": \"Wed Feb 17 13:54:52 BRT 2021\",\n" +
                "    \"message\": \"Processando solicitação\"\n" +
                "}";
    }

    public static SimulationModel getSimulationItemModel() {
        List<String> debts = new ArrayList<>();
        debts.add("6c0dad3a-f288-48cf-a802-b1a06b7bd695");
        return SimulationModel.builder()
                .groupSimulationId("22b9bc27-e6ed-4466-848e-3e8a788b2ab2")
                .id("31763879-97d7-4160-a172-667beaa376fa")
                .currency("R$")
                .date("2021-02-16T23:55:13.585Z")
                .discountedValue("1600,00")
                .discountPercent("10")
                .idDebts(debts)
                .originalValue("16000,00")
                .installmentValue("1440,00")
                .plots("10")
                .status("Efetivado")
                .valueWithDiscount("14400,00")
                .documentId("12768334073")
                .build();
    }

    public static List<SimulationModel> getLstSimulationModel() {
        List<SimulationModel> lstSimulationModel = new ArrayList<>();
        lstSimulationModel.add(getSimulationItemModel());
        return lstSimulationModel;
    }

}
