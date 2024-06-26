package com.sgma.demosignature.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentName;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "document")
    private List<Proof> proofs;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "operation_id")
    private Operation operation;
}
