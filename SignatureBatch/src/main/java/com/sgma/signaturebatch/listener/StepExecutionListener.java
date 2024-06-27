package com.sgma.signaturebatch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class StepExecutionListener extends StepExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(StepExecutionListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("Starting step: {}", stepExecution.getStepName());
        // Load state before the step starts
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("Finished step: {} with status: {}", stepExecution.getStepName(), stepExecution.getStatus());
        // Save state after the step completes
        return stepExecution.getExitStatus();
    }
}
