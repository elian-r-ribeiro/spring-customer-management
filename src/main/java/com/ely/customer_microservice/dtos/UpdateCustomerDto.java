package com.ely.customer_microservice.dtos;

import jakarta.validation.constraints.Size;

public record UpdateCustomerDto(
        @Size(min = 14, message = "O cnpj deve ter no mínimo 14 caractares.")
        @Size(max = 14, message = "O cnpj dever ter no máximo 14 caracteres.")
        String cnpj,
        String companyName,
        String fantasyName,
        String address
) {
}
