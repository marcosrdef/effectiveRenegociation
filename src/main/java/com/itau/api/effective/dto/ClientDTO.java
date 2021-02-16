package com.itau.api.effective.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String id;
    private String name;
    private String lastName;
    private String age;
    private String birthDay;
}
