package com.invillia.apiBancoCliente.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    @NotBlank(message = "O nome deve ser informado")
    @NotNull(message = "O nome não deve ser nulo")
    private String name;

    @CPF(message = "CPF inválido !")
    @NotBlank(message = "O CPF deve ser informado")
    private String cpf;
}