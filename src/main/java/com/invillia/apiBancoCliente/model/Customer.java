package com.invillia.apiBancoCliente.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @Column(nullable = false)
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @CPF
    @Column(nullable = false, length = 11)
    private String cpf;


}

