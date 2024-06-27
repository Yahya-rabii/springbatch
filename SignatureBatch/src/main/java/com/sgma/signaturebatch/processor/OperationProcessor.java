package com.sgma.signaturebatch.processor;

import com.sgma.signaturebatch.domain.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class OperationProcessor implements ItemProcessor<Operation, Operation> {

    private static final Logger log = LoggerFactory.getLogger(OperationProcessor.class);

    @Override
    public Operation process(Operation operation) throws Exception {
        log.info("Processing operation: {}", operation.getId());
        // Any processing logic can go here
        return operation;
    }
}
