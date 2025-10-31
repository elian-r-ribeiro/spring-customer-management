package com.ely.customer_microservice.controller;

import com.ely.customer_microservice.dtos.AddContactToCustomerDto;
import com.ely.customer_microservice.dtos.CreateCustomerDto;
import com.ely.customer_microservice.entities.Customer;
import com.ely.customer_microservice.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create-customer")
    private ResponseEntity<Customer> createCustomer(@RequestBody @Valid CreateCustomerDto createCustomerDto) {
        return ResponseEntity.ok(customerService.createCustomer(createCustomerDto));
    }

    @PostMapping("/add-contact-to-customer")
    private ResponseEntity<Customer> addContactToCustomer(@RequestBody AddContactToCustomerDto addContactToCustomerDto) {
        return ResponseEntity.ok(customerService.addContactToCustomer(addContactToCustomerDto));
    }
}
