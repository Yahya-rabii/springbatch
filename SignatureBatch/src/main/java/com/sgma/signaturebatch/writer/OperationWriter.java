package com.sgma.signaturebatch.writer;

import com.sgma.signaturebatch.domain.Operation;
import com.sgma.signaturebatch.services.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OperationWriter implements ItemWriter<Operation> {

    private static final Logger log = LoggerFactory.getLogger(OperationWriter.class);

    @Autowired
    private OperationService operationService;

    @Override
    public void write(List<? extends Operation> operations) throws Exception {
        operationService.saveAll((List<Operation>) operations);
        log.info("Saved {} operations", operations.size());
    }
}
