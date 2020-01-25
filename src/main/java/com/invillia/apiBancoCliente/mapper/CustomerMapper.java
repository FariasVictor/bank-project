package com.invillia.apiBancoCliente.mapper;

import com.invillia.apiBancoCliente.model.Customer;
import com.invillia.apiBancoCliente.model.request.CustomerRequest;
import com.invillia.apiBancoCliente.model.request.CustomerUpdateRequest;
import com.invillia.apiBancoCliente.model.response.CustomerResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMapper {

    public Customer customerRequestToCustomer(CustomerRequest customerRequest){
        Customer customer = new Customer();
        customer.setCpf(customerRequest.getCpf());
        customer.setName(customerRequest.getName());

        return customer;
    }

    public List<CustomerResponse> customerListToCustomerResponseList(List<Customer> customers){
        ArrayList<CustomerResponse> customerResponses = new ArrayList<CustomerResponse>();

        for (Customer customer: customers) {
            customerResponses.add(customerToCustomerResponse(customer));
        }
        return customerResponses;
    }

    public CustomerResponse customerToCustomerResponse(Customer customer){
        CustomerResponse customerResponse= new CustomerResponse();
        customerResponse.setName(customer.getName());
        customerResponse.setCpf(customer.getCpf());
        customerResponse.setId(customer.getId());

        return customerResponse;
    }

    public void customerUpdateRequestToCustomer(Customer customer,CustomerUpdateRequest customerUpdateRequest){
        customer.setName(customerUpdateRequest.getName());
        customer.setCpf(customerUpdateRequest.getCpf());
    }
}
