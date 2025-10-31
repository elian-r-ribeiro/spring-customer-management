package com.ely.customer_microservice.services;

import com.ely.customer_microservice.dtos.AddContactToCustomerDto;
import com.ely.customer_microservice.dtos.CreateCustomerDto;
import com.ely.customer_microservice.entities.Contact;
import com.ely.customer_microservice.entities.Customer;
import com.ely.customer_microservice.exceptions.ContactNotFoundException;
import com.ely.customer_microservice.exceptions.CustomerNotFoundException;
import com.ely.customer_microservice.repositories.ContactRepository;
import com.ely.customer_microservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ContactRepository contactRepository;

    public Customer createCustomer(CreateCustomerDto createCustomerDto) {

        final Customer newCustomer = new Customer(
                null,
                createCustomerDto.cnpj(),
                createCustomerDto.companyName(),
                createCustomerDto.fantasyName(),
                createCustomerDto.address(),
                null,
                null
        );

        return customerRepository.save(newCustomer);
    }

    public Customer addContactToCustomer(AddContactToCustomerDto addContactToCustomerDto) {

        Customer customer = findyCustomerById(addContactToCustomerDto.customerId());
        Contact contact = findContactById(addContactToCustomerDto.contactId());

        customer.getContacts().add(contact);
        customerRepository.save(customer);
        return customer;
    }

    private Customer findyCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    private Contact findContactById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
    }
}
