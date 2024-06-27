package com.sgma.signaturebatch.writer;

import com.sgma.signaturebatch.domain.Document;
import com.sgma.signaturebatch.services.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentWriter implements ItemWriter<Document> {

    private static final Logger log = LoggerFactory.getLogger(DocumentWriter.class);

    @Autowired
    private DocumentService documentService;

    @Override
    public void write(List<? extends Document> documents) throws Exception {
        documentService.saveAll((List<Document>) documents);
        log.info("Saved {} documents", documents.size());
    }
}
