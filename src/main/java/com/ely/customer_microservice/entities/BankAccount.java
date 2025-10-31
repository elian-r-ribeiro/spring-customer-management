package com.ely.customer_microservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bank_accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    Long accountId;

    @Column(name = "bank_name")
    String bankName;

    String agency;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    Customer customer;
}
