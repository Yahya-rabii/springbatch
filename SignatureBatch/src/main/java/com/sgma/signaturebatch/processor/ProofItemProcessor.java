package com.sgma.signaturebatch.processor;

import com.sgma.signaturebatch.domain.Document;
import com.sgma.signaturebatch.domain.Proof;
import com.sgma.signaturebatch.reader.ProofItemReader;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProofItemProcessor implements ItemProcessor<Document, List<Proof>> {

    @Autowired
    private ProofItemReader proofItemReader;

    @Override
    public List<Proof> process(Document document) throws Exception {
        return proofItemReader.fetchProofsFromAPI();
    }
}
