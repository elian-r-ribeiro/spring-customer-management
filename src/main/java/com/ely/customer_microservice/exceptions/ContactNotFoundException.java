package com.ely.customer_microservice.exceptions;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(Long id) {
        super(
                "Contato não encontrato com id: " + id
        );
    }
}
