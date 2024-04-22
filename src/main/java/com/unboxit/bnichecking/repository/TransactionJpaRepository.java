package com.unboxit.bnichecking.repository;
import com.unboxit.bnichecking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionJpaRepository extends JpaRepository<Transaction, Long> {
}

