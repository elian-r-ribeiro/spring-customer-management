package com.ely.customer_microservice.dtos;

public record AddContactToCustomerDto(
        Long customerId,
        Long contactId
) {
}
