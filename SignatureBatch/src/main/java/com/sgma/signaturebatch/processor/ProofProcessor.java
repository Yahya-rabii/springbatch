package com.sgma.signaturebatch.processor;

import com.sgma.signaturebatch.domain.Document;
import com.sgma.signaturebatch.domain.Operation;
import com.sgma.signaturebatch.domain.Proof;
import com.sgma.signaturebatch.services.DocumentService;
import com.sgma.signaturebatch.services.OperationService;
import com.sgma.signaturebatch.services.ProofService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProofProcessor implements ItemProcessor<Document, Document> {

    private static final Logger log = LoggerFactory.getLogger(ProofProcessor.class);

    @Autowired
    private ProofService proofService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private RetryTemplate retryTemplate;

    private StepExecution stepExecution;

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
        log.info("Starting step: {}", stepExecution.getStepName());
    }

    @AfterStep
    public void afterStep(StepExecution stepExecution) {
        log.info("Finished step: {} with status: {}", stepExecution.getStepName(), stepExecution.getStatus());
        this.stepExecution = stepExecution;
    }

    @Override
    public Document process(Document document) throws Exception {
        log.info("Processing document: {}", document.getDocumentName());
        RestTemplate restTemplate = new RestTemplate();

        String[] formats = {"PDF", "XML"};
        String[] languages = {"ar", "fr"};

        List<Operation> operations = operationService.findAll();

        for (Operation operation : operations) {
            List<Proof> proofs = new ArrayList<>();
            for (String format : formats) {
                if (format.equals("PDF")) {
                    for (String language : languages) {
                        String url = String.format("http://localhost:9000/documents/proofs?operationId=%d&format=%s&language=%s",
                                operation.getId(), format, language);
                        Proof[] proofArray = fetchProofsWithRetry(url); // Use retry template
                        if (proofArray != null) {
                            Collections.addAll(proofs, proofArray);
                        }
                    }
                } else {
                    String url = String.format("http://localhost:9000/documents/proofs?operationId=%d&format=%s",
                            operation.getId(), format);
                    Proof[] proofArray = fetchProofsWithRetry(url); // Use retry template
                    if (proofArray != null) {
                        Collections.addAll(proofs, proofArray);
                    }
                }
            }
            proofService.saveAll(proofs);
            updateDocumentWithProofs(document, proofs);
        }
        return document;
    }

    private Proof[] fetchProofsWithRetry(String url) {
        return retryTemplate.execute(context -> {
            logUrlAndResponse(url);
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(url, Proof[].class);
        });
    }

    private void logUrlAndResponse(String url) {
        log.info("Hitting URL: {}", url);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("Response body: {}", response);
    }

    private void updateDocumentWithProofs(Document document, List<Proof> proofs) {
        for (Proof proof : proofs) {
            if (document.getDocumentName().equals(proof.getDocumentName())) {
                if ("PDF".equals(proof.getType()) && "ar".equals(proof.getLanguage())) {
                    document.setPdfArGedId(proof.getId());
                    document.setPdfArProofStatus("RECEIVED");
                } else if ("PDF".equals(proof.getType()) && "fr".equals(proof.getLanguage())) {
                    document.setPdfFrGedId(proof.getId());
                    document.setPdfFrProofStatus("RECEIVED");
                } else if ("XML".equals(proof.getType())) {
                    document.setXmlGedId(proof.getId());
                    document.setXmlProofStatus("RECEIVED");
                }
            }
        }
        documentService.save(document); // Save the updated document
        log.info("Updated document: {} with proofs", document.getDocumentName());
    }
}
