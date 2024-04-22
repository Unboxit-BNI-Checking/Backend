package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.repository.AccountJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountJpaRepository repository;

    @GetMapping("/accounts")
    public List<Account> getAllAccount(){
        return repository.findAll();
    }

    @PostMapping("/accounts")
    public Account createAccount(@RequestBody Account account){
        return repository.save(account);
    }

}
