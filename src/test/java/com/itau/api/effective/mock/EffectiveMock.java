package com.itau.api.effective.mock;

import com.itau.api.effective.dto.*;
import com.itau.api.effective.model.EffectiveRenegociationModel;

import java.util.ArrayList;
import java.util.List;

public class EffectiveMock {
    public static EffectiveRequestDTO getEffectiveRequest() {
        return EffectiveRequestDTO.builder()
                .transactionId("c4b5dd52-168e-414d-a482-58f92931bf3a")
                .build();
    }

    public static EffectiveResponseDTO getEffectiveResponse() {
        List<DebtDTO> debts = new ArrayList<>();
        debts.add(DebtDTO.builder()
                .id("5165b780-ec4f-4ea8-9c17-2ebaa2d7affd")
                .currentValue("16000,00")
                .originalValue("10000,00")
                .currency("R$")
                .build());


        return EffectiveResponseDTO.builder()
                .id("2c9c6b23-0b1f-4452-903e-05729137633c")
                .date("2021-02-16T23:55:13.585Z")
                .originalValue("16000,00")
                .transactionId("c4b5dd52-168e-414d-a482-58f92931bf3a")
                .discountedValue("1600,00")
                .discountPercentage("10")
                .plots("10")
                .effectiveValue("14400,00")
                .installmentValue("1440,00")
                .documentId("12768334073")
                .debts(debts)
                .simulationId("31763879-97d7-4160-a172-667beaa376fa")
                .status("Efetivado")
                .currency("R$")
                .build();
    }

    public static EffectiveRenegociationModel getEffectiveModel() {
        return EffectiveRenegociationModel
                .builder()
                .effectiveValue("14400,00")
                .currency("R$")
                .id("2c9c6b23-0b1f-4452-903e-05729137633c")
                .date("2021-02-16T23:55:13.585Z")
                .discountedValue("1600,00")
                .originalValue("16000,00")
                .documentId("12768334073")
                .installmentValue("1440,00")
                .plots("10")
                .discountPercentage("10")
                .simulationId("31763879-97d7-4160-a172-667beaa376fa")
                .transactionId("c4b5dd52-168e-414d-a482-58f92931bf3a")
                .build();
    }

    public static List<EffectiveRenegociationModel> getLstEffectiveModel() {
        List<EffectiveRenegociationModel> lstEffectiveModel = new ArrayList<>();
        lstEffectiveModel.add(getEffectiveModel());
        return lstEffectiveModel;
    }

    public static String getMessageEffective() {
        return "{\n" +
                "    \"transactionId\": \"f260bb85-c16e-4893-9f18-4bc198aa56d7\",\n" +
                "    \"date\": \"Wed Feb 17 13:55:44 BRT 2021\",\n" +
                "    \"message\": \"Processando solicitação\",\n" +
                "    \"documentId\": \"12768334073\",\n" +
                "    \"simulationId\": \"b50cec5f-19d8-46e9-80b7-355003038d9c\",\n" +
                "    \"groupSimulationId\": \"df46fa98-0a06-44c3-95ea-01a06f822ec6\"\n" +
                "}";
    }

}
