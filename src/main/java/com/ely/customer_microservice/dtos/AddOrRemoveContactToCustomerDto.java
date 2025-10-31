package com.ely.customer_microservice.dtos;

import jakarta.validation.constraints.NotNull;

public record AddOrRemoveContactToCustomerDto(
        @NotNull(message = "O id do cliente é obrigatório.")
        Long customerId,
        @NotNull(message = "O id do contato é obrigatório.")
        Long contactId
) {
}
