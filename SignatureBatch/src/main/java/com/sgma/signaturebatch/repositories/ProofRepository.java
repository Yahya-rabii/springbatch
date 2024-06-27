package com.sgma.signaturebatch.repositories;

import com.sgma.signaturebatch.domain.Proof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProofRepository extends JpaRepository<Proof, Long> {}