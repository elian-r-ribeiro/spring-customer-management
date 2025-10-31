package com.ely.customer_microservice.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    Long contactId;

    @Column(name = "phone_number", unique = true)
    String phoneNumber;
}
