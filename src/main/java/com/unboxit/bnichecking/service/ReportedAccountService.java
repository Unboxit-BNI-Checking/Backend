package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.entity.http.response.GetReportedAccount;
import com.unboxit.bnichecking.entity.http.response.GetAllReports;
import com.unboxit.bnichecking.entity.http.response.GetReportedAccountAndAccountByAccountNumber;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.model.ReportedAccount;
import com.unboxit.bnichecking.model.TwitterReport;
import com.unboxit.bnichecking.repository.ReportedAccountJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportedAccountService {
    @Autowired
    private ReportedAccountJpaRepository reportedAccountJpaRepository;
    private AccountService accountService;
    private ReportsService reportsService;
    private TwitterReportService twitterReportService;

    public ReportedAccountService(ReportedAccountJpaRepository reportedAccountJpaRepository, AccountService accountService, ReportsService reportsService, TwitterReportService twitterReportService) {
        this.reportedAccountJpaRepository = reportedAccountJpaRepository;
        this.accountService = accountService;
        this.reportsService = reportsService;
        this.twitterReportService = twitterReportService;
    }
    public List<GetReportedAccount> getReportedAccount(){
        List<GetReportedAccount> results = new ArrayList<>();
        List<ReportedAccount> reportedAccounts = reportedAccountJpaRepository.findAll();

        for (ReportedAccount reported : reportedAccounts) {
            GetReportedAccount getAllReportedAccount = new GetReportedAccount();
            getAllReportedAccount.setReportedAccountId(reported.getReportedAccountId());
            getAllReportedAccount.setReportedAccountNumber(reported.getReportedAccountNumber().getAccountNumber());
            getAllReportedAccount.setStatus(reported.getStatus());
            getAllReportedAccount.setTime_finished(reported.getTime_finished());
            getAllReportedAccount.setCreatedAt(reported.getCreatedAt());
            results.add(getAllReportedAccount);
        }
        return results;
    }

    public List<GetReportedAccount> getReportedAccountsByReportedAccountNumber(String accountNumber) {
        List<GetReportedAccount> results = new ArrayList<>();
        List<ReportedAccount> reportedAccounts = reportedAccountJpaRepository.findReportedAccountsByReportedAccountNumber(accountNumber);
        for (ReportedAccount reported : reportedAccounts) {
            GetReportedAccount getAllReportedAccount = new GetReportedAccount();
            getAllReportedAccount.setReportedAccountId(reported.getReportedAccountId());
            getAllReportedAccount.setReportedAccountNumber(reported.getReportedAccountNumber().getAccountNumber());
            getAllReportedAccount.setStatus(reported.getStatus());
            getAllReportedAccount.setTime_finished(reported.getTime_finished());
            getAllReportedAccount.setCreatedAt(reported.getCreatedAt());
            results.add(getAllReportedAccount);
        }
        return results;
    }

    public long getReportedAccountByReportedAccountNumber(String accountNumber) {
        List<ReportedAccount> reportedAccounts = reportedAccountJpaRepository.findReportedAccountsByReportedAccountNumber(accountNumber);
        return getStatus(reportedAccounts);
    }

    public GetReportedAccount getReportedAccountById(long reportedAccount_Id){
        ReportedAccount reportedAccounts = reportedAccountJpaRepository.findReportedAccountById(reportedAccount_Id);
        return new GetReportedAccount(
                reportedAccounts.getReportedAccountId(),
                reportedAccounts.getReportedAccountNumber().getAccountNumber(),
                reportedAccounts.getTime_finished(),
                reportedAccounts.getStatus(),
                reportedAccounts.getCreatedAt()
        );
    }

    public List<GetAllReports> getReportsById(GetReportedAccount reportedAccount_Id){
        List<GetAllReports> Reports = reportsService.getReportsByReportedAccountId(reportedAccount_Id.getReportedAccountId());
        return Reports;
    }

    public GetReportedAccountAndAccountByAccountNumber getReportedAccountAndAccountByReportedAccountNumber(String accountNumber) {
        Account accounts = accountService.getAccountByAccountNumber(accountNumber);
        List<ReportedAccount> reportedAccounts = reportedAccountJpaRepository.findReportedAccountsByReportedAccountNumber(accountNumber);
        List<GetAllReports> Reports = new ArrayList<>();
        for (ReportedAccount reported : reportedAccounts) {
            Reports.addAll(reportsService.getReportsByReportedAccountId(reported.getReportedAccountId()));
        }
        List<TwitterReport> twitterReports = twitterReportService.getAllTwitterReportByAccountNumber(accountNumber);
        GetReportedAccountAndAccountByAccountNumber results = new GetReportedAccountAndAccountByAccountNumber(
                accountNumber,
                accounts.getUserId().getCustomerName(),
                getStatus(reportedAccounts),
                Reports.size(),
                twitterReports.size()
        );
        return results;
    }

    private long getStatus(List<ReportedAccount> reportedAccounts){
        long getStatus = 0;
        for (ReportedAccount reported : reportedAccounts) {
            if(getStatus < reported.getStatus() && reported.getStatus() < 3){
                getStatus = reported.getStatus();
            }
        }
        if(getStatus == 0){
            getStatus = 1;
        }
        return getStatus;
    }

    public GetReportedAccount updateReportedAccountStatus(Long reportedAccountId, int newStatus) {
        ReportedAccount reportedAccount = reportedAccountJpaRepository.findReportedAccountById(reportedAccountId);
        if (reportedAccount != null) {
            reportedAccount.setStatus(newStatus);
            if(reportedAccount.getStatus() > 2){
                reportedAccount.setTime_finished(LocalDateTime.now());
            }
            reportedAccountJpaRepository.save(reportedAccount);
            return new GetReportedAccount(
                    reportedAccount.getReportedAccountId(),
                    reportedAccount.getReportedAccountNumber().getAccountNumber(),
                    reportedAccount.getTime_finished(),
                    reportedAccount.getStatus(),
                    reportedAccount.getCreatedAt()
            );
        }
        return null;
    }
}
