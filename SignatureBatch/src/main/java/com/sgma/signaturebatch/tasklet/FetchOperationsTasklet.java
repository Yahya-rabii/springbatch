package com.sgma.signaturebatch.tasklet;


import com.sgma.signaturebatch.domain.Operation;
import com.sgma.signaturebatch.services.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class FetchOperationsTasklet implements Tasklet {

    @Autowired
    private OperationService operationService;


        private static final Logger log = LoggerFactory.getLogger(FetchOperationsTasklet.class);

        @Override
        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
            log.info("Executing fetch operations tasklet...");
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:9000/documents/getSignedOperations";
            Operation[] operations = restTemplate.getForObject(url, Operation[].class);

            assert operations != null;
            for (Operation operation : operations) {
                operationService.save(operation);
            }
            return RepeatStatus.FINISHED;
        }


}
