package com.ely.customer_microservice.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "bank_accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    Long accountId;

    @Column(name = "bank_name")
    String bankName;

    String agency;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;
}
