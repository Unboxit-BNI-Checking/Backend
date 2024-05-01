package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.entity.http.response.GetAccountNumberByUserId;
import com.unboxit.bnichecking.entity.http.response.GetAllAccounts;
import com.unboxit.bnichecking.entity.http.response.GetAllUser;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.model.User;
import com.unboxit.bnichecking.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserJpaRepository repository;
    private AccountService accountService;

    public UserService(UserJpaRepository repository, AccountService accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }

    public List<User> getAllUser(){
        return repository.findAll();
    }

    public User getUserByUserId(long userId) {
        return repository.findUserByUserId(userId);
    }

    public User createUser(User newUser){
        return repository.save(newUser);
    }

    public User findUserByUsername(String username){
        return repository.findUserByUsername(username);
    }

    public GetAccountNumberByUserId getAccountNumberByUserId(long userId){
        List<Account> accounts = accountService.getAccountByUserId(userId);
        List<GetAllAccounts> results = new ArrayList<>();

        for (Account account : accounts) {
            GetAllAccounts getAllAccounts = new GetAllAccounts();
            getAllAccounts.setAccountId(account.getAccountId());
            getAllAccounts.setAccountNumber(account.getAccountNumber());
            getAllAccounts.setCustomerName(hideCustomerName(account.getUserId().getCustomerName()));
            getAllAccounts.setBlocked(account.getBlocked());
            results.add(getAllAccounts);
        }

        return new GetAccountNumberByUserId(
                userId,
                results
        );
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
}
