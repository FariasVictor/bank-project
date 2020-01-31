package com.invillia.apiBancoCliente.service;

import com.invillia.apiBancoCliente.exception.CustomerNotFoundException;
import com.invillia.apiBancoCliente.mapper.CustomerMapper;
import com.invillia.apiBancoCliente.model.Customer;
import com.invillia.apiBancoCliente.model.request.CustomerRequest;
import com.invillia.apiBancoCliente.model.request.CustomerUpdateRequest;
import com.invillia.apiBancoCliente.model.response.CustomerResponse;
import com.invillia.apiBancoCliente.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public Long insert(CustomerRequest customerRequest){
        Customer customer= customerMapper.customerRequestToCustomer(customerRequest);
        return customerRepository.save(customer).getId();
    }

    public List<CustomerResponse> findAll(){
        List<CustomerResponse> customerResponses= customerMapper.customerListToCustomerResponseList(customerRepository.findAll());
        return customerResponses;
    }

    public CustomerResponse findById(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        CustomerResponse customerResponse= customerMapper.customerToCustomerResponse(customer);
        return customerResponse;
    }

    public List<CustomerResponse> findByName(String name){
        List<Customer> customers = customerRepository.findByNameContaining(name).orElseThrow(CustomerNotFoundException::new);
        List<CustomerResponse> customerResponses = customerMapper.customerListToCustomerResponseList(customers);
        return customerResponses;
    }
    @Transactional
    public void delete(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        customerRepository.delete(customer);
    }

    @Transactional
    public void update(CustomerUpdateRequest customerUpdateRequest,Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        customerMapper.customerUpdateRequestToCustomer(customer,customerUpdateRequest);
        customerRepository.save(customer);
    }
}
