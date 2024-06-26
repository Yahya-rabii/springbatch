package com.sgma.signaturebatch.config;

import com.sgma.signaturebatch.domain.Document;
import com.sgma.signaturebatch.domain.Proof;
import com.sgma.signaturebatch.processor.DocumentItemProcessor;
import com.sgma.signaturebatch.processor.ProofItemProcessor;
import com.sgma.signaturebatch.reader.DocumentItemReader;
import com.sgma.signaturebatch.writer.DocumentItemWriter;
import com.sgma.signaturebatch.writer.ProofItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DocumentItemReader documentItemReader;

    @Autowired
    private DocumentItemProcessor documentItemProcessor;

    @Autowired
    private DocumentItemWriter documentItemWriter;

    @Autowired
    private ProofItemProcessor proofItemProcessor;

    @Autowired
    private ProofItemWriter proofItemWriter;

    @Bean
    public Job processSignedOperationsJob(JobExecutionListener listener) {
        return jobBuilderFactory.get("processSignedOperationsJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step1())
                .next(step2())
                .next(step3())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Document, Document>chunk(10)
                .reader(documentItemReader)
                .processor(documentItemProcessor)
                .writer(documentItemWriter)
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .<Document, List<Proof>>chunk(10)
                .reader(documentItemReader)
                .processor(proofItemProcessor)
                .writer(proofItemWriter)
                .build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .<Document, Document>chunk(10)
                .reader(documentItemReader)
                .processor(documentItemProcessor)
                .writer(documentItemWriter)
                .build();
    }
}
