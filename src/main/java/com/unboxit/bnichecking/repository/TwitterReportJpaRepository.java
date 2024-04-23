package com.unboxit.bnichecking.repository;

import com.unboxit.bnichecking.model.TwitterReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface TwitterReportJpaRepository extends JpaRepository<TwitterReport, Long> {
    @Query(value="SELECT * FROM twitter_reports WHERE reported_account_number=?1", nativeQuery = true)
    List<TwitterReport> findByAccountNumber(String accountNumber);

    List<TwitterReport> findByCreatedAtAfterAndDeletedAtIsNullAndTwitterUsername(
            @Param("fromDate") LocalDateTime fromDate,
            @Param("twitterUsername") String twitterUsername
    );

    List<TwitterReport> findByCreatedAtAfterAndDeletedAtIsNull(
            @Param("fromDate") LocalDateTime fromDate
    );

    List<TwitterReport> findByTwitterUsername(
            @Param("twitterUsername") String twitterUsername
    );

    @Query(value = "SELECT * FROM twitter_reports tr WHERE " +
            "( TRUE = :#{#fromDate == null} OR tr.post_date > :fromDate) AND " +
            "( CASE WHEN :includeDeleted THEN true ELSE tr.deleted_at IS NULL END) AND" +
            "( TRUE = :#{#twitterUsername == null} OR tr.twitter_username = :twitterUsername)", nativeQuery = true)
    List<TwitterReport> findTwitterReportByQueries(
            @Param("fromDate") Timestamp fromDate,
            @Param("twitterUsername") String twitterUsername,
            @Param("includeDeleted") Boolean includeDeleted);
}
