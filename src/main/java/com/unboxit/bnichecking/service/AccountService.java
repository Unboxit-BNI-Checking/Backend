package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.entity.http.request.CreateAccount;
import com.unboxit.bnichecking.entity.http.response.GetAllAccounts;
import com.unboxit.bnichecking.entity.http.response.GetMyAccount;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.repository.AccountJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {

    @Autowired
    private AccountJpaRepository repository;
    private UserService userService;

    @Value("${account.id}")
    private Long accountId;

    public GetMyAccount getMyAccount() {
        Account myAccount = repository.findAccountByAccountId(accountId);
        return new GetMyAccount(
                myAccount.getAccountId(),
                myAccount.getAccountNumber(),
                myAccount.getUser().getCustomerName(),
                myAccount.getBalance(),
                myAccount.getBlocked(),
                myAccount.getCreatedAt(),
                myAccount.getUpdatedAt(),
                myAccount.getDeletedAt());
    }

    public Account getAccountByAccountId(Long accountId) {
        return repository.findAccountByAccountId(accountId);
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        return repository.findAccountByAccountNumber(accountNumber);
    }

    public List<GetAllAccounts> getAllAccounts() {
        List<GetAllAccounts> results = new ArrayList<>();
        List<Account> accounts = repository.findAll();

        for (Account account : accounts) {
            GetAllAccounts getAllAccounts = new GetAllAccounts();
            getAllAccounts.setAccountId(account.getAccountId());
            getAllAccounts.setAccountNumber(account.getAccountNumber());
            getAllAccounts.setCustomerName(hideCustomerName(account.getUser().getCustomerName()));
            getAllAccounts.setBlocked(account.getBlocked());
            results.add(getAllAccounts);
        }

        return results;
    }

    // TODO: Move to util
    private String hideCustomerName(String name) {
        if (name != null && name.length() >= 5) {
            StringBuilder maskedName = new StringBuilder();
            for (int i = 0; i < name.length(); i++) {
                char c = name.charAt(i);
                if (i < 2 || i > name.length() - 3) {
                    maskedName.append(c);
                } else if (Character.isLetter(c)) {
                    maskedName.append('*');
                } else {
                    maskedName.append(c);
                }
            }
            return maskedName.toString();
        } else {
            return name;
        }
    }

    public Account createAccount(CreateAccount newAccount) {
        Account a = new Account(
                newAccount.getAccountNumber(),
                newAccount.getBalance(),
                newAccount.getBlocked(),
                userService.getUserByUserId(newAccount.getUser())
        );
        return repository.save(a);
    }

    public Map<String, String> getAccountDestinationNameByAccountNumberDestination(List<String> accountNumberDestinations) {
        Map<String, String> mapAccountDestinationNameByAccountNumberDestination = new HashMap<>();
        List<Account> accounts = repository.findAccountsByAccountNumbers(accountNumberDestinations);
        for (Account account : accounts) {
            mapAccountDestinationNameByAccountNumberDestination.put(account.getAccountNumber(), account.getUser()).getCustomerName());
        }
        return mapAccountDestinationNameByAccountNumberDestination;

    }

    public Map<String, String> getAccountSourceNameByAccountNumberSource(String accountNumberSource) {
        Map<String, String> mapAccountSourceNameByAccountNumberSource= new HashMap<>();
        Account account = repository.findAccountByAccountNumber(accountNumberSource);
        mapAccountSourceNameByAccountNumberSource.put(account.getAccountNumber(), account.getUser().getCustomerName());
        return mapAccountSourceNameByAccountNumberSource;

    }

    public void HandleAccountTransaction(String accountNumberSource, String accountNumberDestination, long amount) {
        repository.transferAmount(accountNumberSource, accountNumberDestination, amount);
    }
}
