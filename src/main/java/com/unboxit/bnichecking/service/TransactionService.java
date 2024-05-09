package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.entity.http.request.CreateTransaction;
import com.unboxit.bnichecking.entity.http.request.CreateTransactionWithPassword;
import com.unboxit.bnichecking.entity.http.response.*;
import com.unboxit.bnichecking.model.*;
import com.unboxit.bnichecking.repository.TransactionJpaRepository;
import com.unboxit.bnichecking.util.PasswordHasherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
    private ReportsService reportsService;
    private PasswordHasherService passwordHasherService;

    public TransactionService(TransactionJpaRepository transactionJpaRepository, AccountService accountService, ReportedAccountService reportedAccountService, UserService userService, FavouriteService favouriteService, ReportsService reportsService, PasswordHasherService passwordHasherService) {
        this.transactionJpaRepository = transactionJpaRepository;
        this.accountService = accountService;
        this.reportedAccountService = reportedAccountService;
        this.userService = userService;
        this.favouriteService = favouriteService;
        this.reportsService = reportsService;
        this.passwordHasherService = passwordHasherService;
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
            List<Reports> reports = reportsService.getReportsByTransactionId(transaction.getTransactionId());
            boolean isReported;
            if(reports.isEmpty()){
                isReported = false;
            } else {
                isReported = true;
            }

            results.add(new GetTransactionsByAccountNumberSource(
                    transaction.getTransactionId(),
                    currDestinationAccountNumber,
                    mapAccountDestinationNameByAccountNumberDestination.get(currDestinationAccountNumber),
                    currSourceAccountNumber,
                    mapAccountSourceNameByAccountNumberSource.get(currSourceAccountNumber),
                    transaction.getAmount(),
                    "Transfer BNI",
                    transaction.getCreatedAt(),
                    isReported
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

    public CreateTransactionResponse createTransaction(Transaction newTransaction, String password){
        if(passwordHasherService.checkPassword(password, newTransaction.getAccountNumberSource().getUserId().getHashedPassword())){
            Transaction result = transactionJpaRepository.save(newTransaction);
            Account accountDestination = newTransaction.getAccountNumberDestination();
            Account accountSource = newTransaction.getAccountNumberSource();
            accountService.HandleAccountTransaction(accountSource.getAccountNumber(), accountDestination.getAccountNumber(), newTransaction.getAmount());
            List<GetReportedAccount> reportedAccounts = reportedAccountService.getReportedAccountsByReportedAccountNumber(newTransaction.getAccountNumberDestination().getAccountNumber());
            List<Long> statusAccount = new ArrayList<>();
            long statusNumberDestination;
            for (GetReportedAccount reportedAccount : reportedAccounts) {
                statusAccount.add(reportedAccount.getStatus());
            }
            if(statusAccount.isEmpty()){
                statusNumberDestination = 1L;
            } else if(statusAccount.contains(2L) || statusAccount.contains(3L)){
                if(statusAccount.contains(3L)){
                    statusNumberDestination = 3L;
                } else {
                    statusNumberDestination = 2L;
                }
            } else {
                statusNumberDestination = 1L;
            }
            return new CreateTransactionResponse(
                    result.getTransactionId(),
                    true,
                    accountDestination.getAccountNumber(),
                    accountDestination.getUserId().getCustomerName(),
                    result.getCreatedAt(),
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
        } else {
            return null;
        }
    }

    public ValidateTransactionResponse validateTransaction(Transaction newTransaction, CreateTransaction transaction, long userId){
        Account accountDestination = newTransaction.getAccountNumberDestination();
        Account accountSource = newTransaction.getAccountNumberSource();
        User userSource = newTransaction.getAccountNumberSource().getUserId();
        long checkFavourite = 0;
        String favouriteDescription = "Nothing change in favourite";
        if(transaction.isFavourite()){
            if(favouriteService.checkNameAndAccountNumberFavouritedByUserId(userSource.getUserId(), accountDestination.getAccountNumber(), transaction.getName())){
                checkFavourite = 0;
                favouriteDescription = "Nothing change in favourite";
            } else if(favouriteService.checkAccountNumberFavouritedByUserId(userSource.getUserId(), accountDestination.getAccountNumber())){
                checkFavourite = 2;
                favouriteDescription = "Account number already in favourite";
            } else {
                if(favouriteService.checkNameFavouritedByUserId(userSource.getUserId(), transaction.getName())){
                    checkFavourite = 3;
                    favouriteDescription = "Name is already exits in favourite, please input another name";
                } else {
                    if(accountSource.getUserId().getUserId() == userId){
                        favouriteService.createFavourite(new Favourite(accountDestination, transaction.getName(), userSource));
                        checkFavourite = 1;
                        favouriteDescription = "Favourite added successfully";
                    }
                }
            }
        } else {
            if(favouriteService.checkNameAndAccountNumberFavouritedByUserId(userSource.getUserId(), accountDestination.getAccountNumber(), transaction.getName())){
                checkFavourite = 0;
                favouriteDescription = "Nothing change in favourite";
            } else if(favouriteService.checkAccountNumberFavouritedByUserId(userSource.getUserId(), accountDestination.getAccountNumber())){
                checkFavourite = 2;
                favouriteDescription = "Account number already in favourite";
            } else {
                checkFavourite = 4;
                favouriteDescription = "Transfer biasa, nothing change in favourite, muncul pop up";
            }
        }
        List<GetReportedAccount> reportedAccounts = reportedAccountService.getReportedAccountsByReportedAccountNumber(newTransaction.getAccountNumberDestination().getAccountNumber());
        List<Long> statusAccount = new ArrayList<>();
        long statusNumberDestination;
        for (GetReportedAccount reportedAccount : reportedAccounts) {
            statusAccount.add(reportedAccount.getStatus());
        }
        if(statusAccount.isEmpty()){
            statusNumberDestination = 1L;
        } else if(statusAccount.contains(2L) || statusAccount.contains(3L)){
            if(statusAccount.contains(3L)){
                statusNumberDestination = 3L;
            } else {
                statusNumberDestination = 2L;
            }
        } else {
            statusNumberDestination = 1L;
        }
        return new ValidateTransactionResponse(
                accountDestination.getAccountNumber(),
                accountDestination.getUserId().getCustomerName(),
                "BNI",
                statusNumberDestination,
                accountSource.getUserId().getCustomerName(),
                accountSource.getAccountNumber(),
                checkFavourite,
                favouriteDescription,
                newTransaction.getAmount(),
                newTransaction.getNote(),
                0,
                newTransaction.getAmount()
        );
    }
}
