package com.unboxit.bnichecking.repository;
import com.unboxit.bnichecking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionJpaRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM transactions WHERE account_number_source = :accountNumberSource ORDER BY created_at DESC", nativeQuery = true)
    List<Transaction> findTransactionsByAccountNumberSource(String accountNumberSource);

    @Query(value = "SELECT * FROM transactions WHERE account_number_destination = :accountNumberDestination", nativeQuery = true)
    List<Transaction> findTransactionsByAccountNumberDestination(String accountNumberDestination);
    Transaction findTransactionByTransactionId(long transaction_Id);
}
