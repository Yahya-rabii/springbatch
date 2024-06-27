package com.sgma.signaturebatch.reader;

import com.sgma.signaturebatch.domain.Document;
import com.sgma.signaturebatch.services.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class DocumentReader implements ItemReader<Document> {

    private static final Logger log = LoggerFactory.getLogger(DocumentReader.class);

    @Autowired
    private DocumentService documentService;

    private Iterator<Document> documentIterator;

    @Override
    public Document read() throws Exception {
        if (documentIterator == null) {
            List<Document> documents = documentService.findAll();
            documentIterator = documents.iterator();
        }

        if (documentIterator.hasNext()) {
            Document document = documentIterator.next();
            log.info("Reading document: {}", document.getDocumentName());
            return document;
        } else {
            documentIterator = null; // Reset for next batch run
            return null;
        }
    }
}
