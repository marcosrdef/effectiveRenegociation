package com.itau.api.effective;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class EffectiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(EffectiveApplication.class, args);
    }
}
