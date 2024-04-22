package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.http.request.CreateAccount;
import com.unboxit.bnichecking.entity.http.response.GetAllAccounts;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.repository.AccountJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountJpaRepository repository;

    @GetMapping("/accounts")
    public List<GetAllAccounts> getAllAccount(){
        List<GetAllAccounts> results = new ArrayList<>();
        List<Account> accounts = repository.findAll();

        for (Account account : accounts) {
            GetAllAccounts getAllAccounts = new GetAllAccounts();
            getAllAccounts.setAccountId(account.getAccountId());
            getAllAccounts.setAccountNumber(account.getAccountNumber());
            getAllAccounts.setCustomerName(account.getCustomerName());
            getAllAccounts.setBalance(account.getBalance());
            getAllAccounts.setBlocked(account.getBlocked());
            results.add(getAllAccounts);
        }

        return results;
    }

    @PostMapping("/accounts")
    public Account createAccount(@RequestBody CreateAccount newAccount){
        Account a = new Account(
                newAccount.getAccountNumber(),
                newAccount.getCustomerName(),
                newAccount.getBalance(),
                newAccount.getBlocked()
        );
        return repository.save(a);
    }

}
