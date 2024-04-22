package com.unboxit.bnichecking.repository;

import com.unboxit.bnichecking.model.TwitterReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwitterReportJpaRepository extends JpaRepository<TwitterReport, Long> {

}
