package com.sgma.demosignature.controllers;

import com.sgma.demosignature.entities.Document;
import com.sgma.demosignature.entities.Operation;
import com.sgma.demosignature.entities.Proof;
import com.sgma.demosignature.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/sign")
    public Operation signDocuments(@RequestBody List<String> documentNames) {
        return documentService.signDocuments(documentNames);
    }

    @GetMapping("/proofs")
    public List<Proof> getProofs(@RequestParam Long operationId, @RequestParam String format, @RequestParam(required = false) String language) {
        return documentService.getProofs(operationId, format, language);
    }

    @GetMapping("getSignedOperations")
    public List<Operation> getOperations() {
        return documentService.getOperations();
    }

    @GetMapping("getDocuments")
    public List<Document> getDocuments() {
        return documentService.getDocuments();
    }
}
