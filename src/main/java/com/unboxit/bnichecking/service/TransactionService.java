package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.entity.http.request.CreateTransaction;
import com.unboxit.bnichecking.entity.http.response.GetTransaction;
import com.unboxit.bnichecking.entity.http.response.GetTransactionsByAccountNumberSource;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.model.Transaction;
import com.unboxit.bnichecking.repository.TransactionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<GetTransaction> getAllTransactions() {
        List<GetTransaction> results = new ArrayList<>();
        List<Transaction> transactions = transactionJpaRepository.findAll();

        for (Transaction transaction : transactions) {
            GetTransaction getAllTransaction = new GetTransaction(
                transaction.getTransactionId(),
                transaction.getAccountNumberSource().getAccountNumber(),
                transaction.getAccountNumberDestination().getAccountNumber(),
                transaction.getAmount(),
                transaction.getNote(),
                transaction.getCreatedAt()
            );
            results.add(getAllTransaction);
        }

        return results;

    }

    public GetTransaction getTransactionById(long transaction_Id) {
        Transaction transaction = transactionJpaRepository.findTransactionByTransactionId(transaction_Id);
        if (transaction == null) {
            return null;
        }
        return new GetTransaction(
                transaction.getTransactionId(),
                transaction.getAccountNumberSource().getAccountNumber(),
                transaction.getAccountNumberDestination().getAccountNumber(),
                transaction.getAmount(),
                transaction.getNote(),
                transaction.getCreatedAt()
        );
    }

    public List<GetTransactionsByAccountNumberSource> getTransactionByAccountNumberSource(String accountNumber) {
        List<GetTransactionsByAccountNumberSource> results = new ArrayList<>();
        List<Transaction> transactions = transactionJpaRepository.findTransactionsByAccountNumberSource(accountNumber);
        List<String> accountNumberDestinations = new ArrayList<>();;

        for (Transaction transaction : transactions) {
            accountNumberDestinations.add(transaction.getAccountNumberDestination().getAccountNumber());
        }
        Map<String, String> mapAccountDestinationNameByAccountNumberDestination = accountService.getAccountDestinationNameByAccountNumberDestination(accountNumberDestinations);

        for (Transaction transaction : transactions) {
            String currAccountNumber = transaction.getAccountNumberDestination().getAccountNumber();
            results.add(new GetTransactionsByAccountNumberSource(
                    transaction.getTransactionId(),
                    currAccountNumber,
                    mapAccountDestinationNameByAccountNumberDestination.get(currAccountNumber),
                    transaction.getAmount(),
                    "Transfer BNI",
                    transaction.getCreatedAt()
            ));
        }
        return results;
    }

    public List<GetTransaction> getTransactionByAccountNumberDestination(String accountNumber) {
        List<GetTransaction> results = new ArrayList<>();
        List<Transaction> transactions = transactionJpaRepository.findTransactionsByAccountNumberDestination(accountNumber);
        for (Transaction transaction : transactions) {
            GetTransaction getAllTransaction = new GetTransaction(
                    transaction.getTransactionId(),
                    transaction.getAccountNumberSource().getAccountNumber(),
                    transaction.getAccountNumberDestination().getAccountNumber(),
                    transaction.getAmount(),
                    transaction.getNote(),
                    transaction.getCreatedAt()
            );
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
