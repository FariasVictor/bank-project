package com.invillia.apiBancoCliente.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLimitRequest {

    @NotNull
    @DecimalMin(value="300", message = "Limite deve ser maior que 300")
    private double maxOverdraft;
}
