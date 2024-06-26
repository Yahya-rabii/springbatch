package com.sgma.demosignature.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proof {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String language;
    private String type;

    @Lob
    private byte[] content;

    private String documentName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;
}
