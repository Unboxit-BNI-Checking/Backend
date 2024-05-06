package com.unboxit.bnichecking.repository;

import com.unboxit.bnichecking.model.TwitterReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface TwitterReportJpaRepository extends JpaRepository<TwitterReport, Long> {
    @Query(value="SELECT * FROM twitter_reports WHERE reported_account_number=?1", nativeQuery = true)
    List<TwitterReport> findByAccountNumber(String accountNumber);

    @Query(value = "SELECT * FROM twitter_reports tr WHERE " +
            "( TRUE = :#{#fromDate == null} OR tr.post_date > :fromDate) AND " +
            "( CASE WHEN :includeDeleted THEN true ELSE tr.deleted_at IS NULL END) AND" +
            "( TRUE = :#{#twitterUsername == null} OR tr.twitter_username = :twitterUsername)", nativeQuery = true)
    List<TwitterReport> findTwitterReportByQueries(
            @Param("fromDate") Timestamp fromDate,
            @Param("twitterUsername") String twitterUsername,
            @Param("includeDeleted") Boolean includeDeleted);

    @Query(value ="select count(tr.*) from twitter_reports tr  where " +
            "extract(month from tr.created_at) =extract(month from current_date) and extract(year from tr.created_at)=extract(year from current_date)" , nativeQuery = true)
    Long getCount();

}
