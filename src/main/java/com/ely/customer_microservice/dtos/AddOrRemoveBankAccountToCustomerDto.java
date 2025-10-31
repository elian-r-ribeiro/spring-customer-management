package com.ely.customer_microservice.dtos;

import jakarta.validation.constraints.NotNull;

public record AddOrRemoveBankAccountToCustomerDto(
        @NotNull(message = "O id do cliente é obrigatório.")
        Long customerId,
        @NotNull(message = "O id da conta bancária é obrigatório.")
        Long accountId
) {
}
