package com.invillia.apiBancoCliente.controller;

import com.invillia.apiBancoCliente.model.request.AccountRequest;
import com.invillia.apiBancoCliente.model.request.OperationRequest;
import com.invillia.apiBancoCliente.model.request.UpdateLimitRequest;
import com.invillia.apiBancoCliente.model.response.AccountResponse;
import com.invillia.apiBancoCliente.service.AccountService;
import com.invillia.apiBancoCliente.service.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RestController
@CrossOrigin("*")
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;
    private Operations operations;

    @Autowired
    public AccountController(AccountService accountService, Operations operations) {
        this.accountService = accountService;
        this.operations = operations;
    }

    @GetMapping
    public List<AccountResponse> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public AccountResponse findById(@PathVariable long id) {
        return accountService.findById(id);
    }

    @PostMapping
    public HttpEntity<?> insert(@Valid @RequestBody AccountRequest accountRequest) {
        Long id = accountService.insert(accountRequest);

        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path(("/{id}")).build(id);

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateMaxOverdraft(@PathVariable Long id, @Valid @RequestBody UpdateLimitRequest updateLimitRequest) {
        accountService.update(id, updateLimitRequest);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable long id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("deposit/{id}")
    public HttpEntity<?> deposit(@PathVariable("id") Long id, @Valid @RequestBody OperationRequest depositRequest) {
        operations.deposit(id, depositRequest);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("withdraw/{id}")
    public HttpEntity<?> withdraw(@PathVariable("id") Long id, @Valid @RequestBody OperationRequest depositRequest) {
        operations.withdraw(id, depositRequest);
        return ResponseEntity.noContent().build();
    }


}

