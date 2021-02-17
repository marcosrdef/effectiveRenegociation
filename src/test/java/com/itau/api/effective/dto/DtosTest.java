package com.itau.api.effective.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;

@ExtendWith(MockitoExtension.class)
public class DtosTest {

    @Test
    void testDtos() {
        assertThat(ErrorDTO.class, allOf(hasValidBeanConstructor(), hasValidBeanEquals(),
                hasValidBeanHashCode(), hasValidGettersAndSetters(), hasValidBeanToString()));
        assertThat(ClientDTO.class, allOf(hasValidBeanConstructor(), hasValidBeanEquals(),
                hasValidBeanHashCode(), hasValidGettersAndSetters(), hasValidBeanToString()));
        assertThat(ConsumerDTO.class, allOf(hasValidBeanConstructor(), hasValidBeanEquals(),
                hasValidBeanHashCode(), hasValidGettersAndSetters(), hasValidBeanToString()));
        assertThat(DebtDTO.class, allOf(hasValidBeanConstructor(), hasValidBeanEquals(),
                hasValidBeanHashCode(), hasValidGettersAndSetters(), hasValidBeanToString()));
        assertThat(DebtsRequestDTO.class, allOf(hasValidBeanConstructor(), hasValidBeanEquals(),
                hasValidBeanHashCode(), hasValidGettersAndSetters(), hasValidBeanToString()));
        assertThat(EffectiveRequestDTO.class, allOf(hasValidBeanConstructor(), hasValidBeanEquals(),
                hasValidBeanHashCode(), hasValidGettersAndSetters(), hasValidBeanToString()));
        assertThat(EffectiveResponseDTO.class, allOf(hasValidBeanConstructor(), hasValidBeanEquals(),
                hasValidBeanHashCode(), hasValidGettersAndSetters(), hasValidBeanToString()));
        assertThat(SimulationItemDTO.class, allOf(hasValidBeanConstructor(), hasValidBeanEquals(),
                hasValidBeanHashCode(), hasValidGettersAndSetters(), hasValidBeanToString()));
        assertThat(SimulationRequestDTO.class, allOf(hasValidBeanConstructor(), hasValidBeanEquals(),
                hasValidBeanHashCode(), hasValidGettersAndSetters(), hasValidBeanToString()));
        assertThat(SimulationResponseDTO.class, allOf(hasValidBeanConstructor(), hasValidBeanEquals(),
                hasValidBeanHashCode(), hasValidGettersAndSetters(), hasValidBeanToString()));
    }
}
