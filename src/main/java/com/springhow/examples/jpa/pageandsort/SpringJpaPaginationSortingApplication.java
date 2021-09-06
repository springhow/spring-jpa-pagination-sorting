package com.springhow.examples.jpa.pageandsort;

import com.springhow.examples.jpa.pageandsort.domain.entites.Account;
import com.springhow.examples.jpa.pageandsort.domain.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class SpringJpaPaginationSortingApplication implements CommandLineRunner {


    @Autowired
    private AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaPaginationSortingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Pageable pageable = PageRequest.of(2, 40);
        Page<Account> thirdPage = accountRepository.findAllAccounts(pageable);
        System.out.println(thirdPage.getContent());

        Pageable pageableWithSort = PageRequest.of(0, 15, Sort.by("balance"));
        Page<Account> first15AccountsWithLowBalance = accountRepository.findAllAccounts(pageableWithSort);
        System.out.println(first15AccountsWithLowBalance.getContent());

        Pageable pageableWithSortDirection = PageRequest.of(0, 15, Sort.by("balance").descending());
        Page<Account> largest15AccountsByBalance = accountRepository.findAllAccounts(pageableWithSortDirection);
        System.out.println(largest15AccountsByBalance);

        System.out.println("Total results : " + thirdPage.getTotalElements());
        System.out.println("Total Pages : " + thirdPage.getTotalPages());
        System.out.println("Current Page number : " + thirdPage.getNumber());
        System.out.println("Size per page : " + thirdPage.getSize());
        System.out.println("Elements in this page : " + thirdPage.getNumberOfElements());

        Pageable pageRequest = PageRequest.of(0, 20);
        Page<Account> accountsPage;
        do {
            accountsPage = accountRepository.findAllAccounts(pageRequest);
            System.out.println(accountsPage.getContent());
            pageRequest = pageRequest.next();
        } while (!accountsPage.isLast());

    }
}
