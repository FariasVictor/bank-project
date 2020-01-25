package com.invillia.apiBancoCliente.repository;

import com.invillia.apiBancoCliente.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
