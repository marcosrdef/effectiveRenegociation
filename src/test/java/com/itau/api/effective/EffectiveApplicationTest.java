package com.itau.api.effective;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EffectiveApplicationTest {

    @InjectMocks
    private EffectiveApplication effectiveApplication;

    @Test
    void testMain() {
        effectiveApplication.main(new String[]{});
    }
}
