package com.unboxit.bnichecking.repository;
import com.unboxit.bnichecking.model.ReportedAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportedAccountJpaRepository extends JpaRepository<ReportedAccount, Long> {
    @Query(value="SELECT * FROM reported_account WHERE reported_account_number=?1", nativeQuery = true)
    List<ReportedAccount> findReportedAccountsByReportedAccountNumber(String account_number_source);
    @Query(value="SELECT * FROM reported_account WHERE reported_account_number=?1", nativeQuery = true)
    ReportedAccount findReportedAccountByReportedAccountNumber(String account_number_source);
    @Query(value="SELECT * FROM reported_account WHERE reported_account_id=?1", nativeQuery = true)
    ReportedAccount findReportedAccountById(long reported_account_id);

    @Query(value = "select avg(r.waktu_penyelesaian) from (select ra.reported_account_id , (ra.time_finished  - ra.created_at)/ 86400 as waktu_penyelesaian from reported_account ra where ra.time_finished is not null) as r ", nativeQuery = true)
    Long getAverageWaktuPenyelesaianReports();

    @Query(value = "select ra.status , count(reported_account_id)as jumlah from reported_account ra group by status",nativeQuery = true)
    List<Object[]> getCountReportAccountByStatus();
}

