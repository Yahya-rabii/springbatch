
package com.sgma.signaturebatch.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Operation {

    @Id
    private Long id;

    @OneToMany(mappedBy = "operation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documents;


    public Operation() {
    }

    public Operation(Long operationId, List<Document> documents) {
        this.id = operationId;
        this.documents = documents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long operationId) {
        this.id = operationId;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
