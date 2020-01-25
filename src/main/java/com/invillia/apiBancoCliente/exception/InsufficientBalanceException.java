package com.invillia.apiBancoCliente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE, reason="Saldo insuficiente")
public class InsufficientBalanceException extends RuntimeException {
}
