package com.itau.api.effective.service;

import com.itau.api.effective.dto.DebtsRequestDTO;
import com.itau.api.effective.dto.SimulateRequestDTO;
import com.itau.api.effective.model.DebtModel;

import java.util.List;
import java.util.Optional;

public interface DebtsModelService {
    Optional<List<DebtModel>> findDebtsByDocument(SimulateRequestDTO simulateRequest);
}
