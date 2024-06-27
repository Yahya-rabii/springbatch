package com.sgma.signaturebatch.repositories;

import com.sgma.signaturebatch.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {}