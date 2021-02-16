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
public class SimulationItemDTO {
    private String id;
    private String date;
    private String originalValue;
    private String valueWithDiscount;
    private String discountedValue;
    private String discountPercent;
    private String plots;
    private String installmentValue;
    private String documentId;
    private List<DebtDTO> idDebts;
    private String status;
    private String groupSimulationId;
    private String currency;
}
