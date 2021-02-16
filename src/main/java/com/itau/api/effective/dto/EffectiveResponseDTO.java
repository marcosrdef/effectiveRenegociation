package com.itau.api.effective.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EffectiveResponseDTO {
    private String id;
    private String documentId;
    private String transactionId;
    private String originalValue;
    private String discountPercentage;
    private String discountedValue;
    private String effectiveValue;
    private String date;
    private String plots;
    private String installmentValue;
    private String status;
    private String simulationId;
    private String currency;
    private List<DebtDTO> debts;
}
