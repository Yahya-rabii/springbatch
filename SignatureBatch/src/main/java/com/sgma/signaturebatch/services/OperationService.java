package com.sgma.signaturebatch.services;

import com.sgma.signaturebatch.domain.Operation;
import com.sgma.signaturebatch.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OperationService {
    @Autowired
    private OperationRepository operationRepository;

    public Operation save(Operation operation) {
        return operationRepository.save(operation);
    }

    public List<Operation> saveAll(List<Operation> operations) {
        return operationRepository.saveAll(operations);
    }

    public List<Operation> findAll() {
        return operationRepository.findAll();
    }
}
