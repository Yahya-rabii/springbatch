package com.sgma.signaturebatch.processor;

import com.sgma.signaturebatch.domain.Document;
import com.sgma.signaturebatch.domain.Proof;
import com.sgma.signaturebatch.repositories.DocumentRepository;
import com.sgma.signaturebatch.services.ProofService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentItemProcessor implements ItemProcessor<Document, Document> {

    @Autowired
    private ProofService proofService;

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public Document process(Document document) throws Exception {
        List<Proof> proofs = proofService.findAllByDocumentName(document.getDocumentName());

        Document existingDocument = documentRepository.findByDocumentName(document.getDocumentName());
        if (existingDocument == null) {
            existingDocument = document;
        }

        for (Proof proof : proofs) {
            if ("ar".equals(proof.getLanguage()) && "PDF".equals(proof.getType())) {
                existingDocument.setPdfArGedId(proof.getId());
                existingDocument.setPdfArProofStatus("COMPLETED");
            } else if ("fr".equals(proof.getLanguage()) && "PDF".equals(proof.getType())) {
                existingDocument.setPdfFrGedId(proof.getId());
                existingDocument.setPdfFrProofStatus("COMPLETED");
            } else if ("XML".equals(proof.getType())) {
                existingDocument.setXmlGedId(proof.getId());
                existingDocument.setXmlProofStatus("COMPLETED");
            }
        }

        return existingDocument;
    }
}
