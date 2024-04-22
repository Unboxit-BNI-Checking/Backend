package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.http.request.CreateAccount;
import com.unboxit.bnichecking.entity.http.response.GetAllAccounts;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.repository.AccountJpaRepository;
import com.unboxit.bnichecking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<GetAllAccounts> getAllAccount(){

        return accountService.getAllAccounts();
    }

    @PostMapping("/accounts")
    public Account createAccount(@RequestBody CreateAccount newAccount){

        return accountService.createAccount(newAccount);
    }

}
