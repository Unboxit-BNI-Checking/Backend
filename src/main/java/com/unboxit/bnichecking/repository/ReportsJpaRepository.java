package com.unboxit.bnichecking.repository;
import com.unboxit.bnichecking.entity.db.GetReportsAndTransactionByCustomerName;
import com.unboxit.bnichecking.model.Reports;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public interface ReportsJpaRepository extends JpaRepository<Reports, Long> {
    @Query(value="SELECT * FROM reports WHERE reported_account_id=?1", nativeQuery = true)
    List<Reports> findReportsByReportedAccountId(long reported_account_id);
    @Query(value=
            "SELECT r.report_id, r.created_at created_at_reports, r.reported_account_id, t.account_number_source, t.account_number_destination, t.amount, t.created_at created_at_transaction, r.chronology " +
            "FROM reports r INNER JOIN transactions t ON r.transaction_id = t.transaction_id WHERE t.account_number_source IN " +
            "(SELECT account_number FROM accounts a WHERE a.customer_name like CONCAT('%', ?1, '%'))", nativeQuery = true)
    List<Object[]> findReportsAndTransactionByAccountName(String accountName);
}

