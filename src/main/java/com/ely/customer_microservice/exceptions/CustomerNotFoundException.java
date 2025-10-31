package com.ely.customer_microservice.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super(
                "Não foi possível encontrar o cliente informado com id: " + id
        );
    }
}
