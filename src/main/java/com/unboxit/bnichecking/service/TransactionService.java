package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.entity.http.request.CreateTransaction;
import com.unboxit.bnichecking.entity.http.response.GetAllTransaction;
import com.unboxit.bnichecking.model.Transaction;
import com.unboxit.bnichecking.repository.TransactionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionJpaRepository transactionJpaRepository;

    @Autowired
    private AccountService accountService;

    public TransactionService(TransactionJpaRepository transactionJpaRepository, AccountService accountService) {
        this.transactionJpaRepository = transactionJpaRepository;
        this.accountService = accountService;
    }
    public List<GetAllTransaction> getAllTransactions() {
        List<GetAllTransaction> results = new ArrayList<>();
        List<Transaction> transactions = transactionJpaRepository.findAll();

        for (Transaction transaction : transactions) {
            GetAllTransaction getAllTransaction = new GetAllTransaction();
            getAllTransaction.setTransactionId(transaction.getTransactionId());
            getAllTransaction.setAccountNumberSource(transaction.getAccountNumberSource().getAccountNumber());
            getAllTransaction.setAccountNumberDestination(transaction.getAccountNumberDestination().getAccountNumber());
            getAllTransaction.setAmount(transaction.getAmount());
            getAllTransaction.setNote(transaction.getNote());
            getAllTransaction.setCreatedAt(transaction.getCreatedAt());
            results.add(getAllTransaction);
        }

        return results;

    }

    public List<GetAllTransaction> getTransactionById(long transaction_Id) {
        List<GetAllTransaction> results = new ArrayList<>();
        List<Transaction> transactions = transactionJpaRepository.findTransactionById(transaction_Id);
        for (Transaction transaction : transactions) {
            GetAllTransaction getAllTransaction = new GetAllTransaction();
            getAllTransaction.setTransactionId(transaction.getTransactionId());
            getAllTransaction.setAccountNumberSource(transaction.getAccountNumberSource().getAccountNumber());
            getAllTransaction.setAccountNumberDestination(transaction.getAccountNumberDestination().getAccountNumber());
            getAllTransaction.setAmount(transaction.getAmount());
            getAllTransaction.setNote(transaction.getNote());
            getAllTransaction.setCreatedAt(transaction.getCreatedAt());
            results.add(getAllTransaction);
        }
        return results;
    }

    public List<GetAllTransaction> getTransactionByAccountNumberSource(String accountNumber) {
        List<GetAllTransaction> results = new ArrayList<>();
        List<Transaction> transactions = transactionJpaRepository.findTransactionByAccountNumberSource(accountNumber);
        for (Transaction transaction : transactions) {
            GetAllTransaction getAllTransaction = new GetAllTransaction();
            getAllTransaction.setTransactionId(transaction.getTransactionId());
            getAllTransaction.setAccountNumberSource(transaction.getAccountNumberSource().getAccountNumber());
            getAllTransaction.setAccountNumberDestination(transaction.getAccountNumberDestination().getAccountNumber());
            getAllTransaction.setAmount(transaction.getAmount());
            getAllTransaction.setNote(transaction.getNote());
            getAllTransaction.setCreatedAt(transaction.getCreatedAt());
            results.add(getAllTransaction);
        }
        return results;
    }

    public List<GetAllTransaction> getTransactionByAccountNumberDestination(String accountNumber) {
        List<GetAllTransaction> results = new ArrayList<>();
        List<Transaction> transactions = transactionJpaRepository.findTransactionByAccountNumberDestination(accountNumber);
        for (Transaction transaction : transactions) {
            GetAllTransaction getAllTransaction = new GetAllTransaction();
            getAllTransaction.setTransactionId(transaction.getTransactionId());
            getAllTransaction.setAccountNumberSource(transaction.getAccountNumberSource().getAccountNumber());
            getAllTransaction.setAccountNumberDestination(transaction.getAccountNumberDestination().getAccountNumber());
            getAllTransaction.setAmount(transaction.getAmount());
            getAllTransaction.setNote(transaction.getNote());
            getAllTransaction.setCreatedAt(transaction.getCreatedAt());
            results.add(getAllTransaction);
        }
        return results;
    }

    public Transaction createTransaction(Transaction newTransaction){
        return transactionJpaRepository.save(newTransaction);
    }
    
    public Transaction createTransaction(CreateTransaction newTransaction){

        Transaction a = new Transaction(
                accountService.getAccountByAccountNumber(newTransaction.getAccountNumberSource()),
                accountService.getAccountByAccountNumber(newTransaction.getAccountNumberDestination()),
                newTransaction.getAmount(),
                newTransaction.getNote()
        );
        return transactionJpaRepository.save(a);
    }
}
