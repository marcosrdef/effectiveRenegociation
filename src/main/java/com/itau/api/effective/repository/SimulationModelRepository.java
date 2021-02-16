package com.itau.api.effective.repository;

import com.itau.api.effective.model.SimulationModel;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface SimulationModelRepository extends CrudRepository<SimulationModel, String> {
    Optional<List<SimulationModel>> findByDocumentId(String documentId);
    Optional<List<SimulationModel>> findByGroupSimulationId(String groupSimulationId);
    Optional<SimulationModel> findById(String Id);
}
