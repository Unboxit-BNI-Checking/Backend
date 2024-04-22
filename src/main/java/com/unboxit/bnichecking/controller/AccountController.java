package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.http.request.CreateAccount;
import com.unboxit.bnichecking.entity.http.response.ApiResponse;
import com.unboxit.bnichecking.entity.http.response.GetAllAccounts;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse<List<GetAllAccounts>>> getAllAccount(){
        return ResponseEntity.ok(new ApiResponse<>(true, accountService.getAllAccounts(), null));
    }

    @PostMapping("/accounts")
    public ResponseEntity<ApiResponse<Account>> createAccount(@RequestBody CreateAccount newAccount){

        if (accountService.getAccountByAccountNumber(newAccount.getAccountNumber()) != null) {
            ApiResponse<Account> response = new ApiResponse<>(false, null, "Account with this account number already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Account createdAccount = accountService.createAccount(newAccount);
        ApiResponse<Account> response = new ApiResponse<>(true, createdAccount, null);
        return ResponseEntity.ok(response);

    }

}
