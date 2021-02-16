package com.itau.api.effective.dto;

import com.itau.api.effective.model.EffectiveRenegociationModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EffectiveRequestDTO {
    private String transactionId;
    private String date;
    private String message;
    private String documentId;
    private String simulationId;
    private String groupSimulationId;
}
