package com.ely.customer_microservice.exceptions;

public class BankAccountAlreadyHasOwnerException extends RuntimeException {
    public BankAccountAlreadyHasOwnerException(Long id) {
        super(
                "A conta bancária de id " + id + " já tem um dono."
        );
    }
}
