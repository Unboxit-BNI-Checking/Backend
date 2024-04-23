package com.unboxit.bnichecking.repository;
import com.unboxit.bnichecking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionJpaRepository extends JpaRepository<Transaction, Long> {
    @Query(value="SELECT * FROM transaction WHERE account_number_source=?1", nativeQuery = true)
    Transaction findTransactionByAccountNumberSource(String account_number_source);
}
