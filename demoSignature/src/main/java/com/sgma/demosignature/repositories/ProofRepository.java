package com.sgma.demosignature.repositories;

import com.sgma.demosignature.entities.Proof;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProofRepository extends JpaRepository<Proof, Long> {
    List<Proof> findByDocumentOperationIdAndTypeAndLanguage(Long operationId, String type, String language);

    List<Proof> findByDocumentOperationIdAndType(Long operationId, String type);
}
