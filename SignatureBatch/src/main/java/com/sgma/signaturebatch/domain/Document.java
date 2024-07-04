package com.sgma.signaturebatch.domain;


import javax.persistence.*;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documentName;
    private String pdfArProofStatus;
    private String pdfFrProofStatus;
    private String xmlProofStatus;
    private Long pdfArGedId;
    private Long pdfFrGedId;
    private Long xmlGedId;

    @ManyToOne
    private Operation operation;



public Document() {
    }


    public Document(Long id, String documentName, String pdfArProofStatus, String pdfFrProofStatus, String xmlProofStatus, Long pdfArGedId, Long pdfFrGedId, Long xmlGedId, Operation operation) {
        this.id = id;
        this.documentName = documentName;
        this.pdfArProofStatus = pdfArProofStatus;
        this.pdfFrProofStatus = pdfFrProofStatus;
        this.xmlProofStatus = xmlProofStatus;
        this.pdfArGedId = pdfArGedId;
        this.pdfFrGedId = pdfFrGedId;
        this.xmlGedId = xmlGedId;
        this.operation = operation;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPdfArProofStatus() {
        return pdfArProofStatus;
    }

    public void setPdfArProofStatus(String pdfArProofStatus) {
        this.pdfArProofStatus = pdfArProofStatus;
    }

    public String getPdfFrProofStatus() {
        return pdfFrProofStatus;
    }

    public void setPdfFrProofStatus(String pdfFrProofStatus) {
        this.pdfFrProofStatus = pdfFrProofStatus;
    }

    public String getXmlProofStatus() {
        return xmlProofStatus;
    }

    public void setXmlProofStatus(String xmlProofStatus) {
        this.xmlProofStatus = xmlProofStatus;
    }

    public Long getPdfArGedId() {
        return pdfArGedId;
    }

    public void setPdfArGedId(Long pdfArGedId) {
        this.pdfArGedId = pdfArGedId;
    }

    public Long getPdfFrGedId() {
        return pdfFrGedId;
    }

    public void setPdfFrGedId(Long pdfFrGedId) {
        this.pdfFrGedId = pdfFrGedId;
    }

    public Long getXmlGedId() {
        return xmlGedId;
    }

    public void setXmlGedId(Long xmlGedId) {
        this.xmlGedId = xmlGedId;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
