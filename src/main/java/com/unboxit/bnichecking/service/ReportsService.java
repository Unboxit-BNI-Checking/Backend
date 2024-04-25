package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.entity.db.GetReportsAndTransactionByCustomerName;
import com.unboxit.bnichecking.entity.http.response.GetAllReports;
import com.unboxit.bnichecking.entity.http.response.GetAllTransaction;
import com.unboxit.bnichecking.model.ReportedAccount;
import com.unboxit.bnichecking.model.Reports;
import com.unboxit.bnichecking.model.Transaction;
import com.unboxit.bnichecking.repository.ReportsJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportsService {
    @Autowired
    private ReportsJpaRepository reportsJpaRepository;

    public ReportsService(ReportsJpaRepository reportsRepository) {
        this.reportsJpaRepository = reportsRepository;
    }

    public List<GetAllReports> getReports(){
        List<GetAllReports> results = new ArrayList<>();
        List<Reports> reports = reportsJpaRepository.findAll();

        for (Reports report : reports) {
            GetAllReports getAllReports = new GetAllReports();
            getAllReports.setReportId(report.getReportId());
            getAllReports.setTransactionId(report.getTransaction().getTransactionId());
            getAllReports.setChronology(report.getChronology());
            getAllReports.setReportedAccountId(report.getReportedAccount().getReportedAccountId());
            results.add(getAllReports);
        }

        return results;
    }

    public List<GetAllReports> getReportsByReportedAccountId(long reportedAccount_Id){
        List<GetAllReports> results = new ArrayList<>();
        List<Reports> reports = reportsJpaRepository.findReportsByReportedAccountId(reportedAccount_Id);

        for (Reports report : reports) {
            GetAllReports getAllReports = new GetAllReports();
            getAllReports.setReportId(report.getReportId());
            getAllReports.setTransactionId(report.getTransaction().getTransactionId());
            getAllReports.setChronology(report.getChronology());
            results.add(getAllReports);
        }

        return results;
    }

    public List<GetReportsAndTransactionByCustomerName> getReportsAndTransactionByCustomerNames(String accountUsername){
        List<Object[]> results = reportsJpaRepository.findReportsAndTransactionByAccountName(accountUsername);
        List<GetReportsAndTransactionByCustomerName> resultList = new ArrayList<>();

        for (Object[] result : results) {
            GetReportsAndTransactionByCustomerName reportsAndTransactionByCustomerName = new GetReportsAndTransactionByCustomerName();
            reportsAndTransactionByCustomerName.setReportsId((long) result[0]);
            reportsAndTransactionByCustomerName.setCreatedAtReports(((Timestamp) result[1]).toLocalDateTime());
            reportsAndTransactionByCustomerName.setReportedAccountId((long) result[2]);
            reportsAndTransactionByCustomerName.setAccountNumberSource((String) result[3]);
            reportsAndTransactionByCustomerName.setAccountNumberDestination((String) result[4]);
            reportsAndTransactionByCustomerName.setAmount((long) result[5]);
            reportsAndTransactionByCustomerName.setCreatedAtTransaction(((Timestamp) result[6]).toLocalDateTime());
            reportsAndTransactionByCustomerName.setChronology((String) result[7]);
            resultList.add(reportsAndTransactionByCustomerName);
        }
        return resultList;
    }

    public Reports createReports(@RequestBody Reports newReports){
        return this.reportsJpaRepository.save(newReports);
    }
}
