package com.sgma.signaturebatch.services;

import com.sgma.signaturebatch.domain.Document;
import com.sgma.signaturebatch.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> saveAllDocuments(List<Document> documents) {
        return documentRepository.saveAll(documents);
    }

    public void updateDocumentProofs(String documentName, Long arProofId, Long frProofId, Long xmlProofId) {
        Document document = documentRepository.findByDocumentName(documentName);
        if (document != null) {
            if (arProofId != null) {
                document.setPdfArGedId(arProofId);
                document.setPdfArProofStatus("COMPLETED");
            }
            if (frProofId != null) {
                document.setPdfFrGedId(frProofId);
                document.setPdfFrProofStatus("COMPLETED");
            }
            if (xmlProofId != null) {
                document.setXmlGedId(xmlProofId);
                document.setXmlProofStatus("COMPLETED");
            }
            documentRepository.save(document);
        }
    }

    public Document findByDocumentName(String documentName) {
        return documentRepository.findByDocumentName(documentName);
    }
}
