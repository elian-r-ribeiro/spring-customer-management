package com.ely.customer_microservice.exceptions;

import com.ely.customer_microservice.dtos.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    private ResponseEntity<ExceptionResponseDto> handleCustomerNotFoundException(CustomerNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponseDto(exception.getMessage()));
    }

    @ExceptionHandler(ContactNotFoundException.class)
    private ResponseEntity<ExceptionResponseDto> handleContactNotFoundException(CustomerNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponseDto(exception.getMessage()));
    }

    @ExceptionHandler(BankAccountNotFoundException.class)
    private ResponseEntity<ExceptionResponseDto> bankAccountNotFoundException(BankAccountNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponseDto(exception.getMessage()));
    }

    @ExceptionHandler(BankAccountAlreadyHasOwnerException.class)
    private ResponseEntity<ExceptionResponseDto> bankAccountAlreadyHasOwnerException(BankAccountAlreadyHasOwnerException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(exception.getMessage()));
    }

    @ExceptionHandler(CustomerNotBankAccountOwnerException.class)
    private ResponseEntity<ExceptionResponseDto> customerNotBankAccountOwnerException(CustomerNotBankAccountOwnerException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(exception.getMessage()));
    }
}
