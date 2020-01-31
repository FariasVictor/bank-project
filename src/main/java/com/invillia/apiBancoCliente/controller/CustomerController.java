package com.invillia.apiBancoCliente.controller;

import com.invillia.apiBancoCliente.model.request.CustomerRequest;
import com.invillia.apiBancoCliente.model.request.CustomerUpdateRequest;
import com.invillia.apiBancoCliente.model.response.CustomerResponse;
import com.invillia.apiBancoCliente.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public HttpEntity<?> insert(@Valid @RequestBody CustomerRequest customerRequest){
        Long id=customerService.insert(customerRequest);
        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path(("/{id}")).build(id);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public CustomerResponse findByid(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @GetMapping
    public List<CustomerResponse> findAll() {
        return customerService.findAll();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest, @PathVariable Long id){
        customerService.update(customerUpdateRequest,id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public List<CustomerResponse> findByName(@RequestParam(name="name")String name){
        return customerService.findByName(name);
    }
}
