package com.sgma.signaturebatch.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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


    public Document() {
    }



    public Document(Long documentId, String documentName, String pdfArProofStatus, String pdfFrProofStatus, String xmlProofStatus, Long pdfArGedId, Long pdfFrGedId, Long xmlGedId) {
        this.id = documentId;
        this.documentName = documentName;
        this.pdfArProofStatus = pdfArProofStatus;
        this.pdfFrProofStatus = pdfFrProofStatus;
        this.xmlProofStatus = xmlProofStatus;
        this.pdfArGedId = pdfArGedId;
        this.pdfFrGedId = pdfFrGedId;
        this.xmlGedId = xmlGedId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long documentId) {
        this.id = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
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
}
