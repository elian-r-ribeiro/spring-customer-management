package com.ely.customer_microservice.controller;

import com.ely.customer_microservice.dtos.AddOrRemoveBankAccountToCustomerDto;
import com.ely.customer_microservice.dtos.AddOrRemoveContactToCustomerDto;
import com.ely.customer_microservice.dtos.CreateCustomerDto;
import com.ely.customer_microservice.dtos.UpdateCustomerDto;
import com.ely.customer_microservice.entities.Customer;
import com.ely.customer_microservice.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private ResponseEntity<Customer> addContactToCustomer(
            @RequestBody @Valid AddOrRemoveContactToCustomerDto addOrRemoveContactToCustomerDto) {

        return ResponseEntity.ok(customerService.addContactToCustomer(addOrRemoveContactToCustomerDto));
    }

    @PostMapping("/add-bank-account-to-customer")
    private ResponseEntity<Customer> addBankAccountToCustomer(
            @RequestBody @Valid AddOrRemoveBankAccountToCustomerDto addOrRemoveBankAccountToCustomerDto) {

        return ResponseEntity.ok(customerService.addBankAccountToCustomer(addOrRemoveBankAccountToCustomerDto));
    }

    @GetMapping("/get-all-customers")
    private ResponseEntity<List<Customer>> getAllCustomers() {

        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PutMapping("/update-customer/{customerId}")
    private ResponseEntity<Customer> updateCustomerById(
            @PathVariable("customerId") Long customerId, @RequestBody UpdateCustomerDto updateCustomerDto) {

        return ResponseEntity.ok(customerService.updateCustomerById(customerId,updateCustomerDto));
    }

    @DeleteMapping("/remove-contact-from-customer")
    private ResponseEntity<Customer> removeContactFromCustomer(
            @RequestBody @Valid AddOrRemoveContactToCustomerDto addOrRemoveContactToCustomerDto) {

        return ResponseEntity.ok(customerService.removeContactFromCustomer(addOrRemoveContactToCustomerDto));
    }

    @DeleteMapping("/remove-bank-account-from-customer")
    private ResponseEntity<Customer> removeBankAccountFromCustomer(
            @RequestBody @Valid AddOrRemoveBankAccountToCustomerDto addOrRemoveBankAccountToCustomerDto) {

        return ResponseEntity.ok(customerService.removeBankAccountFromCustomer(addOrRemoveBankAccountToCustomerDto));
    }
}
