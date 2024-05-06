package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.entity.http.response.*;
import com.unboxit.bnichecking.model.*;
import com.unboxit.bnichecking.entity.http.response.GetReportedAccount;
import com.unboxit.bnichecking.entity.http.response.GetAllReports;
import com.unboxit.bnichecking.entity.http.response.GetReportedAccountAndAccountByAccountNumber;
import com.unboxit.bnichecking.entity.http.response.GetTotalReportedAccountByStatus;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.model.ReportedAccount;
import com.unboxit.bnichecking.model.TwitterReport;
import com.unboxit.bnichecking.repository.ReportedAccountJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private ReportAttachmentService reportAttachmentService;
    private AdminService adminService;

    public ReportedAccountService(ReportedAccountJpaRepository reportedAccountJpaRepository, AccountService accountService, ReportsService reportsService, TwitterReportService twitterReportService, ReportAttachmentService reportAttachmentService, AdminService adminService) {
        this.reportedAccountJpaRepository = reportedAccountJpaRepository;
        this.accountService = accountService;
        this.reportsService = reportsService;
        this.twitterReportService = twitterReportService;
        this.reportAttachmentService = reportAttachmentService;
        this.adminService = adminService;
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
            if(reported.getStatus() < 4){
                Reports.addAll(reportsService.getReportsByReportedAccountId(reported.getReportedAccountId()));
            }
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
            if(getStatus < reported.getStatus() && reported.getStatus() < 4){
                getStatus = reported.getStatus();
            }
        }
        if(getStatus == 0){
            getStatus = 1;
        }
        return getStatus;
    }

    public GetReportedAccount updateReportedAccountStatus(Long reportedAccountId, int newStatus, long adminId) {
        ReportedAccount reportedAccount = reportedAccountJpaRepository.findReportedAccountById(reportedAccountId);
        if (reportedAccount != null) {
            if(reportedAccount.getAdmins() == null){
                reportedAccount.setAdmins(adminService.findAdminById(adminId));
            }
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

    public List<GetAllReportedAccountDetailReports> getAllReportedAccountDetailReports(long reported_account_id) {
        List<GetAllReportedAccountDetailReports> result = new ArrayList<>();
        ReportedAccount reportedAccounts = reportedAccountJpaRepository.findReportedAccountById(reported_account_id);
        List<Reports> reports = reportsService.getReportsByReportedAccountIdToReports(reportedAccounts.getReportedAccountId());
        List<TwitterReport> twitterReports = twitterReportService.getAllTwitterReportByAccountNumber(reportedAccounts.getReportedAccountNumber().getAccountNumber());
        for (Reports report : reports) {
            GetAllReportedAccountDetailReports getAllReportedAccountDetailReports = new GetAllReportedAccountDetailReports();
            getAllReportedAccountDetailReports.setAccountNumberReported(reportedAccounts.getReportedAccountNumber().getAccountNumber());
            getAllReportedAccountDetailReports.setAccountUsernameReported(reportedAccounts.getReportedAccountNumber().getUserId().getCustomerName());
            getAllReportedAccountDetailReports.setStatus(reportedAccounts.getStatus());
            getAllReportedAccountDetailReports.setReportId(report.getReportId());
            getAllReportedAccountDetailReports.setReportsCreatedAt(report.getCreatedAt());
            getAllReportedAccountDetailReports.setAccountNumber(report.getTransaction().getAccountNumberSource().getAccountNumber());
            getAllReportedAccountDetailReports.setAccountUsername(report.getTransaction().getAccountNumberSource().getUserId().getCustomerName());
            getAllReportedAccountDetailReports.setTransactionCreatedAt(report.getTransaction().getCreatedAt());
            getAllReportedAccountDetailReports.setAmount(report.getTransaction().getAmount());
            getAllReportedAccountDetailReports.setChronology(report.getChronology());
            getAllReportedAccountDetailReports.setAttachment(reportAttachmentService.findReportAttachmentByReportId(report.getReportId()).get(0).getFilePath());
            getAllReportedAccountDetailReports.setTwitterReportsCount(twitterReports.size());
            result.add(getAllReportedAccountDetailReports);
        }
        return result;
    }

    public List<GetAllReportedAccount> getAllReportedAccountAndReports() {
        List<ReportedAccount> reportedAccounts = reportedAccountJpaRepository.findAll();
        List<GetAllReportedAccount> results = new ArrayList<>();
        for (ReportedAccount reported : reportedAccounts) {
            GetAllReportedAccount reportedAccount = new GetAllReportedAccount();
            reportedAccount.setReportAccountId(reported.getReportedAccountId());
            reportedAccount.setAccountNumber(reported.getReportedAccountNumber().getAccountNumber());
            reportedAccount.setReportsCount(reportsService.getReportsByReportedAccountId(reported.getReportedAccountId()).size());
            reportedAccount.setStatus(reported.getStatus());
            reportedAccount.setTimeFinished(reported.getTime_finished());
            String adminUsername = reported.getAdmins() != null ? reported.getAdmins().getUsername() : null;
            reportedAccount.setAdmin(adminUsername);
            results.add(reportedAccount);
        }
        return results;
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
