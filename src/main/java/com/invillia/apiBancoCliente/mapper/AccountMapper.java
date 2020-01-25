package com.invillia.apiBancoCliente.mapper;

import com.invillia.apiBancoCliente.model.Account;
import com.invillia.apiBancoCliente.model.request.AccountRequest;
import com.invillia.apiBancoCliente.model.request.UpdateLimitRequest;
import com.invillia.apiBancoCliente.model.response.AccountResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapper {
    public Account accountRequestToAccount(AccountRequest accountRequest) {
        Account account = new Account();
        account.setAvailableOverdraft(accountRequest.getMaxOverdraft());
        account.setBalance(accountRequest.getBalance());
        account.setMaxOverdraft(accountRequest.getMaxOverdraft());
        return account;
    }

    public AccountResponse accountToAccountResponse(Account account) {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(account.getId());
        accountResponse.setAvailableOverdraft(account.getAvailableOverdraft());
        accountResponse.setBalance(account.getBalance());
        accountResponse.setMaxOverdraft(account.getMaxOverdraft());
        accountResponse.setCustomer(account.getCustomer());

        return accountResponse;
    }

    public List<AccountResponse> accountToAccountResponse(List<Account> accounts) {
        ArrayList<AccountResponse> accountResponses = new ArrayList<AccountResponse>();
        for (Account account : accounts) {
            accountResponses.add(accountToAccountResponse(account));
        }
        return accountResponses;
    }

    public void updateMaxOverdraftByUpdateLimitRequest(Account account, UpdateLimitRequest updateLimitRequest) {
        account.setMaxOverdraft(updateLimitRequest.getMaxOverdraft());
    }
}
