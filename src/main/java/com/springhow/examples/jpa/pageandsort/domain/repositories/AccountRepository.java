package com.springhow.examples.jpa.pageandsort.domain.repositories;

import com.springhow.examples.jpa.pageandsort.domain.entites.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("select a from Account a")
    Page<Account> findAllAccounts(Pageable pageable);

}
