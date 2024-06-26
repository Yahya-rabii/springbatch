package com.sgma.demosignature.repositories;

import com.sgma.demosignature.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
