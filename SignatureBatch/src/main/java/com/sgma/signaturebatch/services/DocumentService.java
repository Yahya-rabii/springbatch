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


    public Document save(Document document) {
        return documentRepository.save(document);
    }


    public List<Document> saveAll(List<Document> documents) {
        return documentRepository.saveAll(documents);
    }

    public List<Document> findAll() {
        return documentRepository.findAll();
    }
}