package com.unboxit.bnichecking.repository;

import java.util.List;

import com.unboxit.bnichecking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {

    Account findAccountByAccountNumber(String accountNumber);

    Account findAccountByAccountId(Long accountId);

    @Query(value = "SELECT * FROM accounts a WHERE a.account_number IN :accountNumbers", nativeQuery = true)
    List<Account> findAccountsByAccountNumbers(List<String> accountNumbers);

}
