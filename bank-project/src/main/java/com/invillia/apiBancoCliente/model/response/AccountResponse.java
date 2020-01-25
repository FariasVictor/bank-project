package com.invillia.apiBancoCliente.model.response;

import com.invillia.apiBancoCliente.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AccountResponse {
    private long id;

    private double balance;

    private double availableOverdraft;

    private double maxOverdraft;

    private Customer customer;

}
