package com.invillia.apiBancoCliente.service;

import com.invillia.apiBancoCliente.exception.AccountNotFoundException;
import com.invillia.apiBancoCliente.exception.CustomerNotFoundException;
import com.invillia.apiBancoCliente.mapper.AccountMapper;
import com.invillia.apiBancoCliente.model.Account;
import com.invillia.apiBancoCliente.model.Customer;
import com.invillia.apiBancoCliente.model.request.AccountRequest;
import com.invillia.apiBancoCliente.model.request.UpdateLimitRequest;
import com.invillia.apiBancoCliente.model.response.AccountResponse;
import com.invillia.apiBancoCliente.repository.AccountRepository;
import com.invillia.apiBancoCliente.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private AccountMapper accountMapper;
    private CustomerRepository customerRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.customerRepository = customerRepository;
    }

    public Long insert(AccountRequest accountRequest) {
        Account account = accountMapper.accountRequestToAccount(accountRequest);
        Customer customer = customerRepository.findById(accountRequest.getCustomerId()).orElseThrow(CustomerNotFoundException::new);
        account.setCustomer(customer);
        accountRepository.save(account);
        return account.getId();
    }

    public AccountResponse findById(long id) {
        Account account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
        AccountResponse accountResponse = accountMapper.accountToAccountResponse(account);
        return accountResponse;
    }

    public List<AccountResponse> findAll() {
        List<Account> accounts = accountRepository.findAllByOrderByIdAsc();
        return accountMapper.accountToAccountResponse(accounts);
    }

    public void update(Long id, UpdateLimitRequest updateLimitRequest) {
        Account account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);

        account.setAvailableOverdraft(account.getAvailableOverdraft() + updateLimitRequest.getMaxOverdraft() - account.getMaxOverdraft());
        accountMapper.updateMaxOverdraftByUpdateLimitRequest(account, updateLimitRequest);
        accountRepository.save(account);
    }

    public void delete(long id) {
        Account account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
        accountRepository.delete(account);
    }
}
