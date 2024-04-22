package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.model.Transaction;
import com.unboxit.bnichecking.repository.TransactionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionJpaRepository transactionJpaRepository;

    public TransactionService(TransactionJpaRepository transactionJpaRepository) {
        this.transactionJpaRepository = transactionJpaRepository;
    }
    public List<Transaction> getTransaction(){
        return this.transactionJpaRepository.findAll();
    }

    public Transaction getTransactionById(Long id){
        return this.transactionJpaRepository.findById(id).get();
    }

    public Transaction createTransaction(@RequestBody Transaction newTransaction){
        return this.transactionJpaRepository.save(newTransaction);
    }
    public ResponseEntity<String> updateTransaction(@PathVariable Long id, @RequestBody Transaction updatedTransaction){
        if(this.transactionJpaRepository.findById(id).isEmpty()){
            return ResponseEntity.status(400).body("Reports not found");
        }
        updatedTransaction.setTransactionId(id);
        this.transactionJpaRepository.save(updatedTransaction);
        return ResponseEntity.ok("Success");
    }

    public ResponseEntity<String> deleteTransaction(@PathVariable Long id){
        if(this.transactionJpaRepository.findById(id).isEmpty()){
            return ResponseEntity.status(400).body("Reports not found");
        }
        this.transactionJpaRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
