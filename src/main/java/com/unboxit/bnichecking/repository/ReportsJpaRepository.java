package com.unboxit.bnichecking.repository;
import com.unboxit.bnichecking.model.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportsJpaRepository extends JpaRepository<Reports, Long> {
    @Query(value="SELECT * FROM reports WHERE reported_account_id=?1", nativeQuery = true)
    List<Reports> findReportsByReportedAccountId(long reported_account_id);
    @Query(value=
            "SELECT r.report_id, r.created_at created_at_reports, ra.status, t.account_number_source, t.account_number_destination, t.amount, t.created_at created_at_transaction, r.chronology \n" +
                    "FROM reports r \n" +
                    "INNER JOIN reported_account ra ON ra.reported_account_id = r.reported_account_id \n" +
                    "INNER JOIN transactions t ON r.transaction_id = t.transaction_id \n" +
                    "WHERE t.account_number_source \n" +
                    "IN (SELECT account_number FROM accounts a WHERE a.customer_name like CONCAT('%', ?1, '%'))", nativeQuery = true)
    List<Object[]> findReportsAndTransactionByAccountName(String accountName);

    @Modifying
    @Transactional
    @Query(value = "insert into reported_account (reported_account_number, status, created_at) values (:reported_account_number, :status, :create_at)", nativeQuery = true)
    void insertReportedAccount(@Param("reported_account_number") String reported_account_number, @Param("status") Long status, @Param("create_at") LocalDateTime create_at);

    @Modifying
    @Transactional
    @Query(value = "insert into reports (transaction_id, reported_account_id, chronology, created_at) values (:transaction_id, :reported_account_id, :chronology, :create_at)", nativeQuery = true)
    void insertReports(@Param("transaction_id") Long transaction_id, @Param("reported_account_id") Long reported_account_id, @Param("chronology") String chronology, @Param("create_at") LocalDateTime create_at);
}

