package com.unboxit.bnichecking.repository;

import java.util.List;

import com.unboxit.bnichecking.model.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {

    Account findAccountByAccountNumber(String accountNumber);

    Account findAccountByAccountId(Long accountId);

    @Query(value = "SELECT * FROM accounts a WHERE a.account_number IN :accountNumbers", nativeQuery = true)
    List<Account> findAccountsByAccountNumbers(List<String> accountNumbers);

    @Transactional
    @Modifying
    @Query(value = "UPDATE accounts SET balance = balance - :amount WHERE account_number = :accountNumberSource", nativeQuery = true)
    void deductAmountFromAccount(String accountNumberSource, long amount);

    @Transactional
    @Modifying
    @Query(value = "UPDATE accounts SET balance = balance + :amount WHERE account_number = :accountNumberDestination", nativeQuery = true)
    void addAmountToAccount(String accountNumberDestination, long amount);

    default void transferAmount(String accountNumberSource, String accountNumberDestination, long amount) {
        deductAmountFromAccount(accountNumberSource, amount);
        addAmountToAccount(accountNumberDestination, amount);
    }
}
