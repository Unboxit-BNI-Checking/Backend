package com.unboxit.bnichecking.repository;

import com.unboxit.bnichecking.model.TwitterReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TwitterReportJpaRepository extends JpaRepository<TwitterReport, Long> {
    @Query(value="SELECT * FROM twitter_reports WHERE reported_account_number=?1", nativeQuery = true)
    List<TwitterReport> findByAccountNumber(String accountNumber);

//    List<TwitterReport> findByTwitterReportId(long twitterReportId);
}
