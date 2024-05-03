package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.entity.http.response.*;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.model.ReportedAccount;
import com.unboxit.bnichecking.model.TwitterReport;
import com.unboxit.bnichecking.repository.ReportedAccountJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public long getAverageWaktuPenyelesaianInDays() {
        long avg = 0;
        if(reportedAccountJpaRepository.getAverageWaktuPenyelesaianReports()!=null){
            avg= Long.parseLong(String.valueOf(reportedAccountJpaRepository.getAverageWaktuPenyelesaianReports()));
        }
        return avg ;
    }

    public List<GetTotalReportedAccountByStatus> getCountReportAccountByStatus() {
        List<Object[]> resultlist = reportedAccountJpaRepository.getCountReportAccountByStatus();
        List<GetTotalReportedAccountByStatus> res = new ArrayList<>();
        for (Object[] obj : resultlist) {
            GetTotalReportedAccountByStatus getTotalReportedAccountByStatus= new GetTotalReportedAccountByStatus();
            getTotalReportedAccountByStatus.setStatus((Long) obj[0]);
            getTotalReportedAccountByStatus.setJumlah((Long) obj[1]);
            res.add(getTotalReportedAccountByStatus);
        }
        return res;
    }

    public GetDashboard getReportDashboard(String month){
        GetDashboard res= new GetDashboard();

        res.setTotal_laporan(reportsService.countReports(month));
        res.setTotal_investigate(reportsService.countByReportedAccount_Status(2L, month));
        res.setTotal_laporan_sosmed(twitterReportService.countTwitterReport());
        res.setAvg_waktu_penanganan_laporan(getAverageWaktuPenyelesaianInDays());

        res.setTotal_laporan_selesai(reportsService.getCountReportsCompleted(month));
        res.setTotal_laporan_belum_selesai(reportsService.getCountReportsUncompleted(month));
        System.out.println(reportsService.getCountReportsUncompleted(month));
        return res;
    }
}
