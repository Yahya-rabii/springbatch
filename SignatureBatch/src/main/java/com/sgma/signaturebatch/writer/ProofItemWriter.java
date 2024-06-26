package com.sgma.signaturebatch.writer;

import com.sgma.signaturebatch.domain.Proof;
import com.sgma.signaturebatch.services.ProofService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProofItemWriter implements ItemWriter<List<Proof>> {

    @Autowired
    private ProofService proofService;

    @Override
    public void write(List<? extends List<Proof>> items) throws Exception {
        for (List<Proof> proofs : items) {
            proofService.saveAllProofs(proofs);
        }
    }
}
