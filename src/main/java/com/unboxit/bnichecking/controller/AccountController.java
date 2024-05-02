package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.http.request.CreateAccount;
import com.unboxit.bnichecking.entity.http.response.ApiResponse;
import com.unboxit.bnichecking.entity.http.response.GetAllAccounts;
import com.unboxit.bnichecking.entity.http.response.GetMyAccount;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.model.User;
import com.unboxit.bnichecking.service.AccountService;
import com.unboxit.bnichecking.service.UserService;
import com.unboxit.bnichecking.util.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;
    private final UserService userService;

    @Autowired
    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<ApiResponse<List<GetAllAccounts>>> getAllAccount(@RequestHeader(name = "Authorization") String header) {
        JwtAuthFilter.checkAdminToken(header.substring(7));
        return ResponseEntity.ok(new ApiResponse<>(true, accountService.getAllAccounts(), null));
    }

    @GetMapping("/accounts/me")
    public ResponseEntity<ApiResponse<GetMyAccount>> getMyAccount() {
        return ResponseEntity.ok(new ApiResponse<>(true, accountService.getMyAccount(), null));
    }


    @PostMapping("/accounts")
    public ResponseEntity<ApiResponse<Account>> createAccount(@RequestBody CreateAccount newAccount, @RequestHeader(name = "Authorization") String header) {
        User user = userService.getUserByUserId(JwtAuthFilter.getUserIdFromToken(header.substring(7)));
        if (user == null) {
            ApiResponse<Account> response = new ApiResponse<>(false, null, "User id is invalid");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (newAccount.getAccountNumber().length() != 10) {
            ApiResponse<Account> response = new ApiResponse<>(false, null, "Account number must consists of 10 numbers");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (accountService.getAccountByAccountNumber(newAccount.getAccountNumber()) != null) {
            ApiResponse<Account> response = new ApiResponse<>(false, null, "Account with this account number already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Account createdAccount = accountService.createAccount(new Account(newAccount.getAccountNumber(), user, newAccount.getBalance(), newAccount.getBlocked()));
        ApiResponse<Account> response = new ApiResponse<>(true, createdAccount, null);
        return ResponseEntity.ok(response);
    }

}
