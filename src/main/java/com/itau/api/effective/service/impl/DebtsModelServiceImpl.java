package com.itau.api.effective.service.impl;

import com.itau.api.effective.dto.SimulationRequestDTO;
import com.itau.api.effective.model.DebtModel;
import com.itau.api.effective.repository.DebtsModelRepository;
import com.itau.api.effective.service.DebtsModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DebtsModelServiceImpl implements DebtsModelService {

    private final DebtsModelRepository debtsModelRepository;

    public DebtsModelServiceImpl(final DebtsModelRepository debtsModelRepository) {
        this.debtsModelRepository = debtsModelRepository;
    }

    @Override
    public Optional<List<DebtModel>> findDebtsByDocument(SimulationRequestDTO simulateRequest) {
        return debtsModelRepository.findByDocumentId(simulateRequest.getDocumentId());
    }
}
