package com.sgma.signaturebatch.model;

import com.sgma.signaturebatch.domain.Document;

import java.util.List;

public class Operation {
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