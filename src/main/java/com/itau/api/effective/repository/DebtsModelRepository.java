package com.itau.api.effective.repository;

import com.itau.api.effective.model.DebtModel;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface DebtsModelRepository extends CrudRepository<DebtModel, String> {
    Optional<List<DebtModel>> findByDocumentId(String documentId);
    Optional<DebtModel> findById(String Id);
}
