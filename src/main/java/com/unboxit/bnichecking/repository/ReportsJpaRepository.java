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
                    "IN (SELECT a.account_number FROM accounts a INNER JOIN users u ON a.user_id = u.user_id WHERE u.customer_name like CONCAT('%', ?1, '%'))", nativeQuery = true)
    List<Object[]> findReportsAndTransactionByAccountName(String accountName);

    @Query(value="SELECT report_id FROM reports WHERE transaction_id = :transaction_id and reported_account_id = :reported_account_id and chronology = :chronology and created_at = :create_at", nativeQuery = true)
    Long findReportIdByNewReport(@Param("transaction_id") Long transaction_id, @Param("reported_account_id") Long reported_account_id, @Param("chronology") String chronology, @Param("create_at") LocalDateTime create_at);

    @Modifying
    @Transactional
    @Query(value = "insert into reported_account (reported_account_number, status, created_at) values (:reported_account_number, :status, :create_at)", nativeQuery = true)
    void insertReportedAccount(@Param("reported_account_number") String reported_account_number, @Param("status") Long status, @Param("create_at") LocalDateTime create_at);

    @Modifying
    @Transactional
    @Query(value = "insert into reports (transaction_id, reported_account_id, chronology, created_at) values (:transaction_id, :reported_account_id, :chronology, :create_at);", nativeQuery = true)
    void insertReports(@Param("transaction_id") Long transaction_id, @Param("reported_account_id") Long reported_account_id, @Param("chronology") String chronology, @Param("create_at") LocalDateTime create_at);

    @Query(value = "select count(r.*) from reports r inner join reported_account ra on r.reported_account_id =ra.reported_account_id " +
            "where ra.status =:status and extract(year from r.created_at)=extract(year from current_date) and " +
            "TRUE= :#{#month==null} OR extract(month from r.created_at) =:month",nativeQuery = true)
    long countReportedByStatus(@Param("status") Long status, @Param("month") Long month);

    @Query(value = "SELECT EXTRACT(MONTH from ra.time_finished) as bulan, COUNT(ra.reported_account_id) \n" +
            "FROM reported_account ra  where  EXTRACT(year from ra.time_finished)=EXTRACT(MONTH from now()) GROUP BY EXTRACT(MONTH from ra.time_finished)", nativeQuery = true)
    List<Object[]> getTotalReportByMonth();

    @Query(value ="select count(r.*) from reports r inner join reported_account ra on r.reported_account_id =ra.reported_account_id " +
            "where (ra.status =3 or ra.status =4) and extract(year from r.created_at)=extract(year from current_date) and " +
            "TRUE= :#{#month==null} OR extract(month from r.created_at) =:month " ,nativeQuery = true)
    Long getCountReportsCompleted(@Param("month") Long month);
    @Query(value ="select count(r.*) from reports r inner join reported_account ra on r.reported_account_id =ra.reported_account_id " +
            "where (ra.status =1 or ra.status =2) and extract(year from r.created_at)=extract(year from current_date) and "+
            "TRUE= :#{#month==null} OR extract(month from r.created_at) =:month" ,nativeQuery = true)
    Long
    getCountReportUncompleted(@Param("month") Long month);

    @Query(value ="select count(r) from reports r where " +
            "TRUE= :#{#month==null} OR extract(month from r.created_at) =:month and extract(year from r.created_at)=extract(year from current_date)" , nativeQuery = true)
    Long getCount(@Param("month") Long month);

}

