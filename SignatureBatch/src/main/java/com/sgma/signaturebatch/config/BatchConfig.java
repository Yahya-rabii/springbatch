package com.sgma.signaturebatch.config;

import com.sgma.signaturebatch.domain.Document;
import com.sgma.signaturebatch.listener.JobCompletionNotificationListener;
import com.sgma.signaturebatch.listener.StepExecutionListener;
import com.sgma.signaturebatch.processor.ProofProcessor;
import com.sgma.signaturebatch.reader.DocumentReader;
import com.sgma.signaturebatch.tasklet.FetchOperationsTasklet;
import com.sgma.signaturebatch.writer.DocumentWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private static final Logger log = LoggerFactory.getLogger(BatchConfig.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private StepExecutionListener stepExecutionListener;

    @Bean
    public FetchOperationsTasklet fetchOperationsTasklet() {
        return new FetchOperationsTasklet();
    }

    @Bean
    public Job importOperationJob(JobCompletionNotificationListener listener, Step step1, Step step2) {
        log.info("Initializing importOperationJob...");
        return jobBuilderFactory.get("importOperationJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step1)
                .next(step2)
                .build();
    }

    @Bean
    public Step step1() {
        log.info("Configuring step1...");
        return stepBuilderFactory.get("step1")
                .tasklet(fetchOperationsTasklet())
                .listener(stepExecutionListener)
                .build();
    }


    @Bean
    public Step step2(DocumentReader documentReader, ProofProcessor proofProcessor, DocumentWriter documentWriter) {
        log.info("Configuring step2...");
        return stepBuilderFactory.get("step2")
                .<Document, Document>chunk(10)
                .reader(documentReader)
                .processor(proofProcessor)
                .writer(documentWriter)
                .listener(stepExecutionListener)
                .build();
    }

    @Bean
    public RetryTemplate retryTemplate() {
        log.info("Setting up retry template...");
        RetryTemplate retryTemplate = new RetryTemplate();

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(5); // Number of retry attempts
        retryTemplate.setRetryPolicy(retryPolicy);

        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(2000); // Initial interval in milliseconds
        backOffPolicy.setMultiplier(2.0); // Multiplication factor for each subsequent retry
        backOffPolicy.setMaxInterval(10000); // Maximum interval in milliseconds
        retryTemplate.setBackOffPolicy(backOffPolicy);

        return retryTemplate;
    }
}
