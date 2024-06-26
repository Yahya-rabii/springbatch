package com.sgma.demosignature.repositories;

import com.sgma.demosignature.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
