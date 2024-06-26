package com.sgma.signaturebatch.writer;

import com.sgma.signaturebatch.domain.Document;
import com.sgma.signaturebatch.repositories.DocumentRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentItemWriter implements ItemWriter<Document> {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public void write(List<? extends Document> items) throws Exception {
        documentRepository.saveAll(items);
    }
}
