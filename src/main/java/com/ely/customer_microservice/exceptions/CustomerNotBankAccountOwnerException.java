package com.ely.customer_microservice.exceptions;

public class CustomerNotBankAccountOwnerException extends RuntimeException {
    public CustomerNotBankAccountOwnerException(Long customerId, Long bankAccountId) {
        super(
                "O usuário de id " + customerId + " não é dono da conta de id " + bankAccountId
        );
    }
}
