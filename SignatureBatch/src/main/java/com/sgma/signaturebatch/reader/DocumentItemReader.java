package com.sgma.signaturebatch.reader;

import com.sgma.signaturebatch.domain.Document;
import com.sgma.signaturebatch.model.Operation;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentItemReader implements ItemReader<Document> {

    private final RestTemplate restTemplate = new RestTemplate();
    private int nextDocumentIndex = 0;
    private List<Document> documentList = new ArrayList<>();


    @Value("${all.operations.url}")
    private String operationsUrl;

    @Override
    public Document read() throws Exception {
        if (documentList.isEmpty()) {
            fetchDocumentsFromAPI();
        }

        Document nextDocument = null;

        if (nextDocumentIndex < documentList.size()) {
            nextDocument = documentList.get(nextDocumentIndex);
            nextDocumentIndex++;
        } else {
            nextDocumentIndex = 0;
            documentList = new ArrayList<>();
        }

        return nextDocument;
    }

    private void fetchDocumentsFromAPI() {
        Operation[] operations = restTemplate.getForObject(operationsUrl, Operation[].class);
        if (operations != null) {
            for (Operation operation : operations) {
                for (Document doc : operation.getDocuments()) {
                    documentList.add(new Document(doc.getId(), doc.getDocumentName()));
                }
            }
        }
    }

}
