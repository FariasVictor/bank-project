package com.invillia.apiBancoCliente.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE, reason="Valor inválido")
public class InvalidValueException extends RuntimeException {
    public InvalidValueException(String message){
        super(message);
    }
}
