package com.ely.customer_microservice.services;

import com.ely.customer_microservice.dtos.AddOrRemoveBankAccountToCustomerDto;
import com.ely.customer_microservice.dtos.AddOrRemoveContactToCustomerDto;
import com.ely.customer_microservice.dtos.CreateCustomerDto;
import com.ely.customer_microservice.dtos.UpdateCustomerDto;
import com.ely.customer_microservice.entities.BankAccount;
import com.ely.customer_microservice.entities.Contact;
import com.ely.customer_microservice.entities.Customer;
import com.ely.customer_microservice.exceptions.*;
import com.ely.customer_microservice.repositories.BankAccountRepository;
import com.ely.customer_microservice.repositories.ContactRepository;
import com.ely.customer_microservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

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

    public Customer addContactToCustomer(AddOrRemoveContactToCustomerDto addOrRemoveContactToCustomerDto) {

        final Customer customer = findyCustomerByIdOrThrowException(addOrRemoveContactToCustomerDto.customerId());
        final Contact contact = findContactByIdOrThrowException(addOrRemoveContactToCustomerDto.contactId());

        customer.getContacts().add(contact);
        return customerRepository.save(customer);
    }

    public Customer addBankAccountToCustomer(AddOrRemoveBankAccountToCustomerDto addOrRemoveBankAccountToCustomerDto) {

        final Customer customer = findyCustomerByIdOrThrowException(addOrRemoveBankAccountToCustomerDto.customerId());
        final BankAccount bankAccount = findBankAccountByIdOrThrowException(addOrRemoveBankAccountToCustomerDto.accountId());

        checkIfBankAccountAlreadyHasOwner(bankAccount);

        bankAccount.setCustomer(customer);
        bankAccountRepository.save(bankAccount);

        customer.getBankAccounts().add(bankAccount);
        return customerRepository.save(customer);
    }

    private void checkIfBankAccountAlreadyHasOwner(BankAccount bankAccount) {
        if(bankAccount.getCustomer() != null) {
            throw new BankAccountAlreadyHasOwnerException(bankAccount.getAccountId());
        }
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomerById(Long customerId, UpdateCustomerDto updateCustomerDto) {

        final Customer oldCustomer = findyCustomerByIdOrThrowException(customerId);
        final Customer updatedCustomer = mergeCustomerChanges(updateCustomerDto, oldCustomer);

        return customerRepository.save(updatedCustomer);
    }

    private Customer mergeCustomerChanges(UpdateCustomerDto updateCustomerDto, Customer customer) {

        if(updateCustomerDto.cnpj() != null) {
            customer.setCnpj(updateCustomerDto.cnpj());
        }
        if(updateCustomerDto.companyName() != null) {
            customer.setCompanyName(updateCustomerDto.companyName());
        }
        if(updateCustomerDto.fantasyName() != null) {
            customer.setFantasyName(updateCustomerDto.fantasyName());
        }
        if(updateCustomerDto.address() != null) {
            customer.setAddress(updateCustomerDto.address());
        }

        return customer;
    }

    public Customer removeContactFromCustomer(AddOrRemoveContactToCustomerDto addOrRemoveContactToCustomerDto) {

        final Customer customer = findyCustomerByIdOrThrowException(addOrRemoveContactToCustomerDto.customerId());
        final Contact contact = findContactByIdOrThrowException(addOrRemoveContactToCustomerDto.contactId());

        customer.getContacts().remove(contact);
        return customerRepository.save(customer);
    }

    public Customer removeBankAccountFromCustomer(AddOrRemoveBankAccountToCustomerDto addOrRemoveBankAccountToCustomerDto) {

        final Customer customer = findyCustomerByIdOrThrowException(addOrRemoveBankAccountToCustomerDto.customerId());
        final BankAccount bankAccount = findBankAccountByIdOrThrowException(addOrRemoveBankAccountToCustomerDto.accountId());

        if(customer.getBankAccounts().contains(bankAccount)) {
            customer.getBankAccounts().remove(bankAccount);
            bankAccount.setCustomer(null);

            bankAccountRepository.save(bankAccount);
            return customerRepository.save(customer);
        } else {
            throw new CustomerNotBankAccountOwnerException(
                    addOrRemoveBankAccountToCustomerDto.customerId(), addOrRemoveBankAccountToCustomerDto.accountId());
        }
    }

    private Customer findyCustomerByIdOrThrowException(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    private Contact findContactByIdOrThrowException(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
    }

    private BankAccount findBankAccountByIdOrThrowException(Long id) {
        return bankAccountRepository.findById(id).orElseThrow(() -> new BankAccountNotFoundException(id));
    }
}
