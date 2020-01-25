package com.invillia.apiBancoCliente.service;

import com.invillia.apiBancoCliente.exception.AccountNotFoundException;
import com.invillia.apiBancoCliente.exception.InsufficientBalanceException;
import com.invillia.apiBancoCliente.exception.InvalidValueException;
import com.invillia.apiBancoCliente.model.Account;
import com.invillia.apiBancoCliente.model.request.OperationRequest;
import com.invillia.apiBancoCliente.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Operations {

    private AccountRepository accountRepository;

    @Autowired
    public Operations(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void withdraw(Long id, OperationRequest operationRequest) {

        Account account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);

        double value = operationRequest.getValue();
        double balance = account.getBalance();
        double availableOverdraft = account.getAvailableOverdraft();

        if (value > 0) {
            if (value <= balance + availableOverdraft) {
                if (value > balance) {
                    availableOverdraft = (balance + availableOverdraft) - value;
                    balance = 0;
                } else {
                    balance -= value;
                }
            }else{
                throw new InsufficientBalanceException();
            }
        }else{
            throw new InvalidValueException("Valor deve ser maior que 0");
        }
        account.setAvailableOverdraft(availableOverdraft);
        account.setBalance(balance);
        accountRepository.save(account);
    }

    public void deposit(Long id, OperationRequest depositRequest) {

        Account account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);


        double value = depositRequest.getValue();
        double maxOverdraft = account.getMaxOverdraft();
        double availableOverdraft = account.getAvailableOverdraft();
        double balance = account.getBalance();

        double accountDebt = maxOverdraft - availableOverdraft;
        if (value > 0) {
            if (value >= accountDebt) {
                availableOverdraft = maxOverdraft;
                value -= accountDebt;
                balance += value;
            } else {
                availableOverdraft += value;
            }
        } else {
            throw new InvalidValueException("Valor deve ser maior que 0");
        }
        account.setAvailableOverdraft(availableOverdraft);
        account.setBalance(balance);
        accountRepository.save(account);
    }
}
