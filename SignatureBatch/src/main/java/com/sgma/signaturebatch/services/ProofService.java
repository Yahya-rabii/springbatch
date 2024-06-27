package com.sgma.signaturebatch.services;

import com.sgma.signaturebatch.domain.Proof;
import com.sgma.signaturebatch.repositories.ProofRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProofService {
    @Autowired
    private ProofRepository proofRepository;

    public List<Proof> saveAll(List<Proof> proofs) {
        return proofRepository.saveAll(proofs);
    }

    public List<Proof> findAll() {
        return proofRepository.findAll();
    }
}