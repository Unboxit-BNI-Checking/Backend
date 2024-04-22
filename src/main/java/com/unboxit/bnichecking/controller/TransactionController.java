package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.model.Reports;
import com.unboxit.bnichecking.model.Transaction;
import com.unboxit.bnichecking.service.ReportsService;
import com.unboxit.bnichecking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/transaction", produces = "application/json") //Get Resource
    public List<Transaction> getTransaction(){
        return transactionService.getTransaction();
    }

    @PostMapping(value = "/transaction", consumes = "application/json", produces = "application/json") //Create Resource
    public Transaction createTransaction(@RequestBody Transaction newTransaction){
        return transactionService.createTransaction(newTransaction);
    }

    @GetMapping(value = "/transaction/{id}", produces = "application/json") //Get Resource
    public Transaction getTransaction(@PathVariable Long id){
        return transactionService.getTransactionById(id);
    }

    @PutMapping(value = "/transaction/{id}", produces = "application/json") //Update Resource
    public ResponseEntity<String> updateTransaction(@PathVariable Long id, @RequestBody Transaction updatedTransaction){
        return transactionService.updateTransaction(id, updatedTransaction);
    }

    @DeleteMapping(value = "/transaction/{id}", produces = "application/json") //Delete Resource
    public ResponseEntity<String> deleteTransaction(@PathVariable Long id){
        return transactionService.deleteTransaction(id);
    }
}
