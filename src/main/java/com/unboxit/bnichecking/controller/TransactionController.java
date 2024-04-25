package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.http.request.CreateTransaction;
import com.unboxit.bnichecking.entity.http.response.ApiResponse;
import com.unboxit.bnichecking.entity.http.response.GetAllTransaction;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.model.TwitterReport;
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

    @GetMapping(value = "/transaction/{transaction_Id}")
    public ResponseEntity<ApiResponse<List<GetAllTransaction>>> getTransactionById(@PathVariable long transaction_Id){
        if(transactionService.getTransactionById(transaction_Id).isEmpty()){
            ApiResponse<List<GetAllTransaction>> response = new ApiResponse<>(false, null, "Transaction id is not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(new ApiResponse<>(true, transactionService.getTransactionById(transaction_Id), null));
    }

    @GetMapping(value = "/transaction/account/{account_number_source}")
    public ResponseEntity<ApiResponse<List<GetAllTransaction>>> getAllTransactionByAccountNumberSource(@PathVariable String account_number_source){
        if (accountService.getAccountByAccountNumber(account_number_source) == null) {
            ApiResponse<List<GetAllTransaction>> response = new ApiResponse<>(false, null, "Account with this account number not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        if (transactionService.getTransactionByAccountNumberSource(account_number_source) == null) {
            ApiResponse<List<GetAllTransaction>> response = new ApiResponse<>(true, null, "Account with this account number has no transactions");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.ok(new ApiResponse<>(true, transactionService.getTransactionByAccountNumberSource(account_number_source), null));
    }

    @PostMapping("/transaction")
    public ResponseEntity<ApiResponse<Transaction>> createTransaction(@RequestBody CreateTransaction newTransaction){
        if(newTransaction.getAccountNumberSource() == null || newTransaction.getAccountNumberDestination() == null || newTransaction.getAccountNumberSource().isEmpty() || newTransaction.getAccountNumberDestination().isEmpty()) {
            ApiResponse<Transaction> response = new ApiResponse<>(false, null, "Account number can't be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        if(newTransaction.getAccountNumberSource().length() != 10 || newTransaction.getAccountNumberDestination().length() != 10){
            ApiResponse<Transaction> response = new ApiResponse<>(false, null, "Account number must consist of 10 numbers");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        if(accountService.getAccountByAccountNumber(newTransaction.getAccountNumberSource()) == null){
            ApiResponse<Transaction> response = new ApiResponse<>(false, null, "Account with this source account number not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        if(accountService.getAccountByAccountNumber(newTransaction.getAccountNumberDestination()) == null) {
            ApiResponse<Transaction> response = new ApiResponse<>(false, null, "Destination account with this account number not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        long balance = (accountService.getAccountByAccountNumber(newTransaction.getAccountNumberSource())).getBalance();
        if (newTransaction.getAmount() <= 0) {
            ApiResponse<Transaction> response = new ApiResponse<>(false, null, "Enter the transaction amount correctly");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        if ((balance - newTransaction.getAmount()) < 5000) {
            ApiResponse<Transaction> response = new ApiResponse<>(false, null, "Your balance is not enough to make transactions");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Transaction createdTransaction = transactionService.createTransaction(new Transaction(
                accountService.getAccountByAccountNumber(newTransaction.getAccountNumberSource()),
                accountService.getAccountByAccountNumber(newTransaction.getAccountNumberDestination()),
                newTransaction.getAmount(),
                newTransaction.getNote()));
        ApiResponse<Transaction> response = new ApiResponse<>(true, createdTransaction, null);
        return ResponseEntity.ok(response);
    }
}
