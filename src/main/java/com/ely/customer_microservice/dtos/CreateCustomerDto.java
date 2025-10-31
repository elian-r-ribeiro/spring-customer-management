package com.ely.customer_microservice.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateCustomerDto(
        @NotBlank(message = "O cnpj é obrigatório.")
        String cnpj,
        @NotBlank(message = "O nome da empresa é obrigatório")
        String companyName,
        String fantasyName,
        String address
) {
}
