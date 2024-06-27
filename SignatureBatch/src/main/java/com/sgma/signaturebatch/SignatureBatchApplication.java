package com.sgma.signaturebatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SignatureBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SignatureBatchApplication.class, args);
    }
}
