package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.entity.http.request.CreateAccount;
import com.unboxit.bnichecking.entity.http.response.GetAllAccounts;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.repository.AccountJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountJpaRepository repository;


    public List<GetAllAccounts> getAllAccounts() {
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

    public Account createAccount(CreateAccount newAccount){
        Account a = new Account(
                newAccount.getAccountNumber(),
                newAccount.getCustomerName(),
                newAccount.getBalance(),
                newAccount.getBlocked()
        );
        return repository.save(a);
    }

}
