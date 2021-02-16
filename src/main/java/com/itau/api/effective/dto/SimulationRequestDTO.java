package com.itau.api.effective.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimulationRequestDTO {
    private String groupSimulationId;
    private String documentId;
    private String simulateId;
    private String date;
    private String message;
}
