package com.unboxit.bnichecking.repository;
import com.unboxit.bnichecking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionJpaRepository extends JpaRepository<Transaction, Long> {
    @Query(value="SELECT * FROM transactions WHERE account_number_source=?1", nativeQuery = true)
    List<Transaction> findTransactionByAccountNumberSource(String account_number_source);
    @Query(value="SELECT * FROM transactions WHERE account_number_destination=?1", nativeQuery = true)
    List<Transaction> findTransactionByAccountNumberDestination(String account_number_source);
    @Query(value="SELECT * FROM transactions WHERE transaction_Id=?1", nativeQuery = true)
    List<Transaction> findTransactionById(long transaction_Id);
}
