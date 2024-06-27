package com.sgma.signaturebatch.reader;

import com.sgma.signaturebatch.domain.Operation;
import com.sgma.signaturebatch.services.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class OperationReader implements ItemReader<Operation> {

    private static final Logger log = LoggerFactory.getLogger(OperationReader.class);

    @Autowired
    private OperationService operationService;

    private Iterator<Operation> operationIterator;

    @Override
    public Operation read() throws Exception {
        if (operationIterator == null) {
            List<Operation> operations = operationService.findAll();
            operationIterator = operations.iterator();
        }

        if (operationIterator.hasNext()) {
            Operation operation = operationIterator.next();
            log.info("Reading operation: {}", operation.getId());
            return operation;
        } else {
            operationIterator = null; // Reset for next batch run
            return null;
        }
    }
}
