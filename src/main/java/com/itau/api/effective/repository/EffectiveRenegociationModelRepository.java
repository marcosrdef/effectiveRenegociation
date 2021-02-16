package com.itau.api.effective.repository;

import com.itau.api.effective.model.EffectiveRenegociationModel;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface EffectiveRenegociationModelRepository extends CrudRepository<EffectiveRenegociationModel, String> {
    Optional<List<EffectiveRenegociationModel>> findByDocumentId(String documentId);
    Optional<EffectiveRenegociationModel> findById(String Id);
    Optional<EffectiveRenegociationModel> findByTransactionId(String findByTransactionId);

}
