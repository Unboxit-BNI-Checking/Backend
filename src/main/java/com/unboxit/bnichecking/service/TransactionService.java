package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.entity.http.response.*;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.model.Transaction;
import com.unboxit.bnichecking.model.User;
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
    private UserService userService;
    private FavouriteService favouriteService;

    public TransactionService(TransactionJpaRepository transactionJpaRepository, AccountService accountService, ReportedAccountService reportedAccountService, UserService userService, FavouriteService favouriteService) {
        this.transactionJpaRepository = transactionJpaRepository;
        this.accountService = accountService;
        this.reportedAccountService = reportedAccountService;
        this.userService = userService;
        this.favouriteService = favouriteService;
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

    public CreateTransactionResponse getTransactionById(long transaction_Id) {
        Transaction transaction = transactionJpaRepository.findTransactionByTransactionId(transaction_Id);
        if (transaction == null) {
            return null;
        }
        return new CreateTransactionResponse(
                transaction.getTransactionId(),
                true,
                transaction.getAccountNumberDestination().getAccountNumber(),
                transaction.getAccountNumberDestination().getUserId().getCustomerName(),
                transaction.getCreatedAt(),
                "",
                "BNI",
                0,
                transaction.getAccountNumberSource().getUserId().getCustomerName(),
                transaction.getAccountNumberSource().getAccountNumber(),
                transaction.getNote(),
                transaction.getAmount(),
                0,
                transaction.getAmount()
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

    public GetTransactionValidationByAccountNumber getTransactionValidationByAccountNumber(String accountNumberSource, String accountNumberDestination) {
        accountService.getAccountByAccountNumber(accountNumberSource).getUserId().getCustomerName();
        Account accountDestination = accountService.getAccountByAccountNumber(accountNumberDestination);
        return new GetTransactionValidationByAccountNumber(
                accountService.getAccountByAccountNumber(accountNumberDestination).getAccountNumber(),
                accountService.getAccountByAccountNumber(accountNumberDestination).getUserId().getCustomerName(),
                accountService.getAccountByAccountNumber(accountNumberSource).getAccountNumber(),
                accountService.getAccountByAccountNumber(accountNumberSource).getUserId().getCustomerName(),
                reportedAccountService.getReportedAccountByReportedAccountNumber(accountNumberDestination)
        );
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
                accountDestination.getUserId().getCustomerName(),
                newTransaction.getCreatedAt(),
                "",
                "BNI",
                statusNumberDestination,
                accountSource.getUserId().getCustomerName(),
                accountSource.getAccountNumber(),
                newTransaction.getNote(),
                newTransaction.getAmount(),
                0,
                newTransaction.getAmount()
        );
    }

    public ValidateTransactionResponse validateTransaction(Transaction newTransaction){
        Account accountDestination = newTransaction.getAccountNumberDestination();
        Account accountSource = newTransaction.getAccountNumberSource();
        User userSource = newTransaction.getAccountNumberSource().getUserId();
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
        return new ValidateTransactionResponse(
                accountDestination.getAccountNumber(),
                accountDestination.getUserId().getCustomerName(),
                "BNI",
                statusNumberDestination,
                accountSource.getUserId().getCustomerName(),
                accountSource.getAccountNumber(),
                favouriteService.checkAccountNumberFavouritedByUserId(userSource.getUserId(), accountDestination.getAccountNumber()),
                newTransaction.getAmount(),
                newTransaction.getNote(),
                0,
                newTransaction.getAmount()
        );
    }
}
