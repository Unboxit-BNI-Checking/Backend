package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.http.request.CreateTransaction;
import com.unboxit.bnichecking.entity.http.response.ApiResponse;
import com.unboxit.bnichecking.entity.http.response.GetAllTransaction;
import com.unboxit.bnichecking.service.AccountService;
import com.unboxit.bnichecking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.unboxit.bnichecking.model.Transaction;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {
    private final TransactionService transactionService;
    private final AccountService accountService;

    @Autowired
    public TransactionController(TransactionService transactionService, AccountService accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @GetMapping(value = "/transaction")
    public ResponseEntity<ApiResponse<List<GetAllTransaction>>> getAllTransaction(){
        return ResponseEntity.ok(new ApiResponse<>(true, transactionService.getAllTransactions(), null));
    }

    @GetMapping(value = "/transaction/{account_number_source}")
    public ResponseEntity<ApiResponse<List<GetAllTransaction>>> getAllTransactionByAccountNumberSource(@PathVariable String account_number_source){
        //kalau AccountNumberSource
        if (accountService.getAccountByAccountNumber(account_number_source)!=null) {
            ApiResponse<List<GetAllTransaction>> response = new ApiResponse<>(false, null, "Account with this account number already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        if (transactionService.getTransactionByAccountNumberSource(account_number_source) == null) {
            ApiResponse<List<GetAllTransaction>> response = new ApiResponse<>(false, null, "Account with this account number already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.ok(new ApiResponse<>(true, transactionService.getTransactionByAccountNumberSource(account_number_source), null));
    }

    @PostMapping("/accounts")
    public ResponseEntity<ApiResponse<Transaction>> createTransaction(@RequestBody CreateTransaction newTransaction){
        if (transactionService.getTransactionByAccountNumberSource(newTransaction.getAccountNumberSource()) != null) {
            ApiResponse<Transaction> response = new ApiResponse<>(false, null, "Account with this account number already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Transaction createdTransaction = transactionService.createTransaction(newTransaction);
        ApiResponse<Transaction> response = new ApiResponse<>(true, createdTransaction, null);
        return ResponseEntity.ok(response);
    }
}
