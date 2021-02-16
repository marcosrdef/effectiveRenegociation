package com.itau.api.effective.controller;

import com.itau.api.effective.dto.EffectiveRequestDTO;
import com.itau.api.effective.dto.SimulateRequestDTO;
import com.itau.api.effective.model.EffectiveRenegociationModel;
import com.itau.api.effective.model.SimulationModel;
import com.itau.api.effective.service.EffectiveRenegociationService;
import com.itau.api.effective.service.SimulationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EffectiveController {
    private final SimulationService simulationService;
    private final EffectiveRenegociationService effectiveRenegociationService;

    public EffectiveController(final EffectiveRenegociationService effectiveRenegociationService,
                               final SimulationService simulationService) {

        this.effectiveRenegociationService = effectiveRenegociationService;
        this.simulationService = simulationService;

    }

    @GetMapping("/renegociation")
    public ResponseEntity<EffectiveRenegociationModel> renegociation(
            @RequestBody final EffectiveRequestDTO effectiveRequest) {
        return ResponseEntity.ok(effectiveRenegociationService.findByTransactionId(effectiveRequest));
    }

    @GetMapping("/simulations")
    public ResponseEntity<List<SimulationModel>> simulation(
            @RequestBody final SimulateRequestDTO simulateRequest) {
        return ResponseEntity.ok(simulationService.findByGroupSimulationId(simulateRequest));
    }

}
