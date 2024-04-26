package com.unboxit.bnichecking.repository;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.model.ReportedAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportedAccountJpaRepository extends JpaRepository<ReportedAccount, Long> {
    @Query(value="SELECT * FROM reported_account WHERE reported_account_number=?1", nativeQuery = true)
    List<ReportedAccount> findReportedAccountsByReportedAccountNumber(String account_number_source);
    @Query(value="SELECT * FROM reported_account WHERE reported_account_number=?1", nativeQuery = true)
    ReportedAccount findReportedAccountByReportedAccountNumber(String account_number_source);
    @Query(value="SELECT * FROM reported_account WHERE reported_account_id=?1", nativeQuery = true)
    ReportedAccount findReportedAccountById(long reported_account_id);
}

