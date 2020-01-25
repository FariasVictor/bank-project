package com.invillia.apiBancoCliente.model.request;


import com.invillia.apiBancoCliente.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    @NotNull
    @DecimalMin(value = "0", message = "Saldo deve ser positivo")
    private double balance;

    @NotNull
    @DecimalMin(value= "300", message= "Limite deve ser maior que 300")
    private double maxOverdraft;

    @NotNull
    private Long customerId;
}
