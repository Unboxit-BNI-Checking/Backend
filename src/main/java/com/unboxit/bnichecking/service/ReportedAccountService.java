package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.entity.http.response.GetAllReportedAccount;
import com.unboxit.bnichecking.entity.http.response.GetAllReports;
import com.unboxit.bnichecking.model.ReportedAccount;
import com.unboxit.bnichecking.model.Reports;
import com.unboxit.bnichecking.model.Transaction;
import com.unboxit.bnichecking.repository.ReportedAccountJpaRepository;
import com.unboxit.bnichecking.repository.ReportsJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportedAccountService {
    @Autowired
    private ReportedAccountJpaRepository reportedAccountJpaRepository;
    private ReportsService reportsService;

    public ReportedAccountService(ReportedAccountJpaRepository reportedAccountJpaRepository, ReportsService reportsService) {
        this.reportedAccountJpaRepository = reportedAccountJpaRepository;
        this.reportsService = reportsService;
    }
    public List<GetAllReportedAccount> getReportedAccount(){
        List<GetAllReportedAccount> results = new ArrayList<>();
        List<ReportedAccount> reportedAccounts = reportedAccountJpaRepository.findAll();

        for (ReportedAccount reported : reportedAccounts) {
            GetAllReportedAccount getAllReportedAccount = new GetAllReportedAccount();
            getAllReportedAccount.setReportedAccountId(reported.getReportedAccountId());
            getAllReportedAccount.setReportedAccountNumber(reported.getReportedAccountNumber().getAccountNumber());
            getAllReportedAccount.setStatus(reported.getStatus());
            getAllReportedAccount.setTime_finished(reported.getTime_finished());
            getAllReportedAccount.setCreatedAt(reported.getCreatedAt());
            results.add(getAllReportedAccount);
        }

        return results;
    }

    public List<GetAllReportedAccount> getReportedAccountByReportedAccountNumber(String accountNumber) {
        List<GetAllReportedAccount> results = new ArrayList<>();
        List<ReportedAccount> reportedAccounts = reportedAccountJpaRepository.findReportedAccountByReportedAccountNumber(accountNumber);
        for (ReportedAccount reported : reportedAccounts) {
            GetAllReportedAccount getAllReportedAccount = new GetAllReportedAccount();
            getAllReportedAccount.setReportedAccountId(reported.getReportedAccountId());
            getAllReportedAccount.setReportedAccountNumber(reported.getReportedAccountNumber().getAccountNumber());
            getAllReportedAccount.setStatus(reported.getStatus());
            getAllReportedAccount.setTime_finished(reported.getTime_finished());
            getAllReportedAccount.setCreatedAt(reported.getCreatedAt());
            results.add(getAllReportedAccount);
        }
        return results;
    }

    public List<GetAllReportedAccount> getListReportedAccountById(long reportedAccount_Id){
        List<GetAllReportedAccount> results = new ArrayList<>();
        List<ReportedAccount> reportedAccounts = reportedAccountJpaRepository.findListReportedAccountById(reportedAccount_Id);
        for (ReportedAccount reportedAccount : reportedAccounts) {
            GetAllReportedAccount getAllReportedAccount = new GetAllReportedAccount();
            getAllReportedAccount.setReportedAccountId(reportedAccount.getReportedAccountId());
            getAllReportedAccount.setReportedAccountNumber(reportedAccount.getReportedAccountNumber().getAccountNumber());
            getAllReportedAccount.setStatus(reportedAccount.getStatus());
            getAllReportedAccount.setTime_finished(reportedAccount.getTime_finished());
            getAllReportedAccount.setCreatedAt(reportedAccount.getCreatedAt());
            results.add(getAllReportedAccount);
        }
        return results;
    }

    public ReportedAccount getReportedAccountById(long reportedAccount_Id){
        ReportedAccount reportedAccounts = reportedAccountJpaRepository.findReportedAccountById(reportedAccount_Id);
        return reportedAccounts;
    }

    public List<GetAllReports> getReportsById(ReportedAccount reportedAccount_Id){
        List<GetAllReports> Reports = reportsService.getReportsByReportedAccountId(reportedAccount_Id.getReportedAccountId());
        return Reports;
    }

    public ReportedAccount createReportedAccount(ReportedAccount newReportedAccount){
        return reportedAccountJpaRepository.save(newReportedAccount);
    }
}
