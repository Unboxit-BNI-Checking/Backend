package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.http.request.CreateTransaction;
import com.unboxit.bnichecking.entity.http.response.*;
import com.unboxit.bnichecking.entity.http.response.GetTransaction;
import com.unboxit.bnichecking.model.Account;
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

    //Get All Transaction
    @GetMapping(value = "/transaction")
    public ResponseEntity<ApiResponse<List<GetTransaction>>> getAllTransaction() {
        List<GetTransaction> transactions = transactionService.getAllTransactions();
        if (transactions.isEmpty()) {
            ApiResponse<List<GetTransaction>> response = new ApiResponse<>(true, null, "Don't have any Transaction");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.ok(new ApiResponse<>(true, transactions, null));
    }

    //Get Transaction By Id
    @GetMapping(value = "/transaction/{transaction_Id}")
    public ResponseEntity<ApiResponse<GetTransaction>> getTransactionById(@PathVariable long transaction_Id) {
        GetTransaction transaction = transactionService.getTransactionById(transaction_Id);
        if (transaction == null) {
            ApiResponse<GetTransaction> response = new ApiResponse<>(false, null, "Transaction id is not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(new ApiResponse<>(true, transaction, null));
    }

    //Get Transaction By Account Number Source
    @GetMapping(value = "/transaction/account/{account_number_source}")
    public ResponseEntity<ApiResponse<List<GetTransactionsByAccountNumberSource>>> getAllTransactionByAccountNumberSource(@PathVariable String account_number_source) {
        if (accountService.getAccountByAccountNumber(account_number_source) == null) {
            ApiResponse<List<GetTransactionsByAccountNumberSource>> response = new ApiResponse<>(false, null, "Account with this account number not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        if (transactionService.getTransactionByAccountNumberSource(account_number_source) == null) {
            ApiResponse<List<GetTransactionsByAccountNumberSource>> response = new ApiResponse<>(true, null, "Account with this account number has no transactions");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.ok(new ApiResponse<>(true, transactionService.getTransactionByAccountNumberSource(account_number_source), null));
    }

    //Post Transaction
    // TODO: refactor response to include account number status
    @PostMapping("/transaction")
    public ResponseEntity<ApiResponse<CreateTransactionResponse>> createTransaction(@RequestBody CreateTransaction newTransaction) {
        if (newTransaction.getAccountNumberSource() == null || newTransaction.getAccountNumberDestination() == null || newTransaction.getAccountNumberSource().isEmpty() || newTransaction.getAccountNumberDestination().isEmpty()) {
            ApiResponse<CreateTransactionResponse> response = new ApiResponse<>(false, null, "Account number source and destination can't be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (newTransaction.getAccountNumberSource().length() != 10 || newTransaction.getAccountNumberDestination().length() != 10) {
            ApiResponse<CreateTransactionResponse> response = new ApiResponse<>(false, null, "Account number must consist of 10 numbers");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Account accountSource = accountService.getAccountByAccountNumber(newTransaction.getAccountNumberSource());
        Account accountDestination = accountService.getAccountByAccountNumber(newTransaction.getAccountNumberDestination());
        if (accountSource == null) {
            ApiResponse<CreateTransactionResponse> response = new ApiResponse<>(false, null, "Account with this account_number_source not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        if (accountDestination == null) {
            ApiResponse<CreateTransactionResponse> response = new ApiResponse<>(false, null, "Account with this account_number_destination not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        if (newTransaction.getAmount() <= 0) {
            ApiResponse<CreateTransactionResponse> response = new ApiResponse<>(false, null, "Enter the transaction amount correctly");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if ((accountSource.getBalance() - newTransaction.getAmount()) < 5000) {
            ApiResponse<CreateTransactionResponse> response = new ApiResponse<>(false, null, "Your balance is not enough to make transactions (balance can't drop below 5000)");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        CreateTransactionResponse response = transactionService.createTransaction(new Transaction(
                accountSource,
                accountDestination,
                newTransaction.getAmount(),
                newTransaction.getNote()));
        return ResponseEntity.ok(new ApiResponse<>(true, response, null));
    }
}

