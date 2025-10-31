package com.ely.customer_microservice.exceptions;

public class BankAccountNotFoundException extends RuntimeException {
    public BankAccountNotFoundException(Long id) {
        super(
                "Conta bancária não encontrada com id " + id
        );
    }
}
