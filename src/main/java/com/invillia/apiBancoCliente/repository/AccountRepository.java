package com.invillia.apiBancoCliente.repository;

import com.invillia.apiBancoCliente.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository  extends JpaRepository<Account, Long> {

    List<Account> findAllByOrderByIdAsc();

}