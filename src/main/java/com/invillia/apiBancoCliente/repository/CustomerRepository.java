package com.invillia.apiBancoCliente.repository;

import com.invillia.apiBancoCliente.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repositoryz
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    public Optional<List<Customer>> findByNameContaining(String name);
}
