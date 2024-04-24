package com.unboxit.bnichecking.repository;
import com.unboxit.bnichecking.model.ReportedAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportedAccountJpaRepository extends JpaRepository<ReportedAccount, Long> {
}

