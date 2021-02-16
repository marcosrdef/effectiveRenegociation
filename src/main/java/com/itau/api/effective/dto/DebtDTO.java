package com.itau.api.effective.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DebtDTO {
    private String id;
    private String currentValue;
    private String originalValue;
    private String currency;
}
