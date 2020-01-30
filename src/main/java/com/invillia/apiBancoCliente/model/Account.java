package com.invillia.apiBancoCliente.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Id
    private long id;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    private double availableOverdraft;

    @Column(nullable = false)
    private double maxOverdraft;

    @ManyToOne
    @JoinColumn(name="customer_id",nullable=false)
    private Customer customer;
}
