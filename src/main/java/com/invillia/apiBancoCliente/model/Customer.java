package com.invillia.apiBancoCliente.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.util.List;


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

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Account> accounts;

}

