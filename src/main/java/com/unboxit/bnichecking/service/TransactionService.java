package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.entity.http.response.CreateTransactionResponse;
import com.unboxit.bnichecking.entity.http.response.GetReportedAccount;
import com.unboxit.bnichecking.entity.http.response.GetTransaction;
import com.unboxit.bnichecking.entity.http.response.GetTransactionsByAccountNumberSource;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.model.Transaction;
import com.unboxit.bnichecking.repository.TransactionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private TransactionJpaRepository transactionJpaRepository;

    @Autowired
    private AccountService accountService;
    private ReportedAccountService reportedAccountService;

    public TransactionService(TransactionJpaRepository transactionJpaRepository, AccountService accountService, ReportedAccountService reportedAccountService) {
        this.transactionJpaRepository = transactionJpaRepository;
        this.accountService = accountService;
        this.reportedAccountService = reportedAccountService;
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
        Map<String, String> mapAccountSourceNameByAccountNumberSource = accountService.getAccountSourceNameByAccountNumberSource(accountNumber);

        for (Transaction transaction : transactions) {
            String currDestinationAccountNumber = transaction.getAccountNumberDestination().getAccountNumber();
            String currSourceAccountNumber = transaction.getAccountNumberSource().getAccountNumber();

            results.add(new GetTransactionsByAccountNumberSource(
                    transaction.getTransactionId(),
                    currDestinationAccountNumber,
                    mapAccountDestinationNameByAccountNumberDestination.get(currDestinationAccountNumber),
                    currSourceAccountNumber,
                    mapAccountSourceNameByAccountNumberSource.get(currSourceAccountNumber),
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

    public CreateTransactionResponse createTransaction(Transaction newTransaction){
        newTransaction = transactionJpaRepository.save(newTransaction);
        Account accountDestination = newTransaction.getAccountNumberDestination();
        Account accountSource = newTransaction.getAccountNumberSource();
        accountService.HandleAccountTransaction(accountSource.getAccountNumber(), accountDestination.getAccountNumber(), newTransaction.getAmount());
        List<GetReportedAccount> reportedAccounts = reportedAccountService.getReportedAccountsByReportedAccountNumber(newTransaction.getAccountNumberDestination().getAccountNumber());
        List<Long> statusAccount = new ArrayList<>();
        int statusNumberDestination;
        for (GetReportedAccount reportedAccount : reportedAccounts) {
            statusAccount.add(reportedAccount.getStatus());
        }
        if(statusAccount.contains(2)){
            statusNumberDestination = 2;
        } else {
            statusNumberDestination = 1;
        }
        return new CreateTransactionResponse(
                newTransaction.getTransactionId(),
                true,
                accountDestination.getAccountNumber(),
                accountDestination.getCustomerName(),
                newTransaction.getCreatedAt(),
                "",
                "BNI",
                statusNumberDestination,
                accountSource.getCustomerName(),
                accountSource.getAccountNumber(),
                newTransaction.getAmount(),
                0,
                newTransaction.getAmount()
        );
    }
}
