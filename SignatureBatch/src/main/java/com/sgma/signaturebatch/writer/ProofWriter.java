package com.sgma.signaturebatch.writer;

import com.sgma.signaturebatch.domain.Proof;
import com.sgma.signaturebatch.services.ProofService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProofWriter implements ItemWriter<List<Proof>> {

    private static final Logger log = LoggerFactory.getLogger(ProofWriter.class);

    @Autowired
    private ProofService proofService;

    @Override
    public void write(List<? extends List<Proof>> items) throws Exception {
        for (List<Proof> proofs : items) {
            proofService.saveAll(proofs);
            log.info("Saved {} proofs", proofs.size());
        }
    }
}
