
package com.sgma.signaturebatch.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Proof {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String language;
    private String type;
    private String content;
    private String documentName;


    public Proof() {
    }

    public Proof(Long proofId, String content, String type, String language, String documentName) {
        this.id = proofId;
        this.content = content;
        this.type = type;
        this.language = language;
        this.documentName = documentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long proofId) {
        this.id = proofId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }
}
