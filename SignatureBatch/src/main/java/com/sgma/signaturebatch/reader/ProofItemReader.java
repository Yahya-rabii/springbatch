package com.sgma.signaturebatch.reader;

import com.sgma.signaturebatch.domain.Document;
import com.sgma.signaturebatch.domain.Proof;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProofItemReader implements ItemReader<Proof> {

    private final RestTemplate restTemplate = new RestTemplate();
    private int nextProofIndex;
    private List<Proof> proofList;


    @Value("${xml.proof.url}")
    private String xmlProofUrl;

    @Value("${pdf.proof.url}")
    private String pdfProofUrl;

    @Value("${all.operations.url}")
    private String operationsUrl;




    @Override
    public Proof read() throws Exception {
        if (proofListIsNotInitialized()) {
            proofList = fetchProofsFromAPI();
        }

        Proof nextProof = null;

        if (nextProofIndex < proofList.size()) {
            nextProof = proofList.get(nextProofIndex);
            nextProofIndex++;
        } else {
            nextProofIndex = 0;
            proofList = null;
        }

        return nextProof;
    }

    private boolean proofListIsNotInitialized() {
        return this.proofList == null;
    }

    public List<Proof> fetchProofsFromAPI() {
        List<Proof> proofs = new ArrayList<>();
        // Fetch operations first
        Operation[] operations = restTemplate.getForObject(operationsUrl, Operation[].class);

        if (operations != null) {
            for (Operation operation : operations) {
                long operationId = operation.getId();
                proofs.addAll(fetchProofsForOperation(operationId, "PDF", "ar"));
                proofs.addAll(fetchProofsForOperation(operationId, "PDF", "fr"));
                proofs.addAll(fetchProofsForOperation(operationId, "XML", null));
            }
        }

        return proofs;
    }

    private List<Proof> fetchProofsForOperation(long operationId, String format, String language) {
        String url;
        if ("XML".equals(format)) {
            url = String.format(xmlProofUrl, operationId, format);
        } else {
            url = String.format(pdfProofUrl, operationId, format, language);
        }

        Proof[] proofs = restTemplate.getForObject(url, Proof[].class);
        return proofs != null ? Arrays.asList(proofs) : new ArrayList<>();
    }

    // Inner class to represent Operation structure for fetching operations
    private static class Operation {
        private long id;
        private List<Document> documents;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public List<Document> getDocuments() {
            return documents;
        }

        public void setDocuments(List<Document> documents) {
            this.documents = documents;
        }
    }
}
