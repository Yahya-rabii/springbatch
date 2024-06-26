package com.sgma.demosignature.services;

import com.sgma.demosignature.entities.Document;
import com.sgma.demosignature.entities.Operation;
import com.sgma.demosignature.entities.Proof;
import com.sgma.demosignature.repositories.DocumentRepository;
import com.sgma.demosignature.repositories.OperationRepository;
import com.sgma.demosignature.repositories.ProofRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private ProofRepository proofRepository;

    public Operation signDocuments(List<String> documentNames) {
        Operation operation = new Operation();

        List<Document> documents = new ArrayList<>();
        for (String name : documentNames) {
            Document document = new Document();
            document.setDocumentName(name);
            document.setOperation(operation);

            // Create proofs for each document
            List<Proof> proofs = new ArrayList<>();

            Proof arProof = new Proof();
            arProof.setLanguage("ar");
            arProof.setType("PDF");
            arProof.setDocument(document);
            arProof.setDocumentName(name);
            arProof.setContent(Base64.getDecoder().decode("QmFzZTY0IGVuY29kZWQgUERGIGZvciBBcmFiaWM=")); // Random base64 encoded PDF content for Arabic
            proofs.add(arProof);

            Proof frProof = new Proof();
            frProof.setLanguage("fr");
            frProof.setType("PDF");
            frProof.setDocument(document);
            frProof.setDocumentName(name);
            frProof.setContent(Base64.getDecoder().decode("QmFzZTY0IGVuY29kZWQgUERGIGZvciBGcmVuY2g=")); // Random base64 encoded PDF content for French
            proofs.add(frProof);

            Proof xmlProof = new Proof();
            xmlProof.setType("XML");
            xmlProof.setDocument(document);
            xmlProof.setDocumentName(name);
            xmlProof.setContent(Base64.getDecoder().decode("PHhzbD5CYXNlNjQgZW5jb2RlZCBYTUwgY29udGVudDwveG1sPg==")); // Random base64 encoded XML content
            proofs.add(xmlProof);

            document.setProofs(proofs);
            documents.add(document);
        }

        operation.setDocuments(documents);
        return operationRepository.save(operation);
    }

    public List<Proof> getProofs(Long operationId, String format, String language) {
        if (language != null && !language.isEmpty()) {
            return proofRepository.findByDocumentOperationIdAndTypeAndLanguage(operationId, format, language);
        } else {
            return proofRepository.findByDocumentOperationIdAndType(operationId, format);
        }
    }

    public List<Operation> getOperations() {
        return operationRepository.findAll();
    }

    public List<Document> getDocuments() {
        return documentRepository.findAll();
    }
}
