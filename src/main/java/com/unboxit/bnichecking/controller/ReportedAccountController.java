package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.http.response.GetReportedAccountAndAccountByAccountNumber;
import com.unboxit.bnichecking.entity.http.response.*;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.model.ReportedAccount;
import com.unboxit.bnichecking.service.AccountService;
import com.unboxit.bnichecking.service.ReportedAccountService;
import com.unboxit.bnichecking.util.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReportedAccountController {
    private final ReportedAccountService reportedAccountService;
    private final AccountService accountService;

    @Autowired
    public ReportedAccountController(ReportedAccountService reportedAccountService, AccountService accountService) {
        this.reportedAccountService = reportedAccountService;
        this.accountService = accountService;
    }

    @GetMapping(value = "/reportedAcc", produces = "application/json")
    public ResponseEntity<ApiResponse<List<GetReportedAccount>>> getAllReportedAccount(){
         return ResponseEntity.ok(new ApiResponse<>(true, reportedAccountService.getReportedAccount(), null));
    }

    @GetMapping(value = "/reportedAcc/{reportedAccount_Id}", produces = "application/json")
    public ResponseEntity<ApiResponse<GetReportedAccount>> getReportedAccountById(@PathVariable long reportedAccount_Id){
        return ResponseEntity.ok(new ApiResponse<>(true, reportedAccountService.getReportedAccountById(reportedAccount_Id), null));
    }

    @GetMapping(value = "/reportedAcc/account/{reported_account_number}", produces = "application/json")
    public ResponseEntity<ApiResponse<List<GetReportedAccount>>> getReportedAccountByReportedAccountNumber(@PathVariable String reported_account_number){
        return ResponseEntity.ok(new ApiResponse<>(true, reportedAccountService.getReportedAccountsByReportedAccountNumber(reported_account_number), null));
    }

    @GetMapping(value = "/reportedAcc/reports/{reportedAccount_Id}", produces = "application/json")
    public ResponseEntity<ApiResponse<List<GetAllReports>>> getReportsByReportedAccountId(@PathVariable long reportedAccount_Id){
        GetReportedAccount reportedAccount = reportedAccountService.getReportedAccountById(reportedAccount_Id);
        return ResponseEntity.ok(new ApiResponse<>(true, reportedAccountService.getReportsById(reportedAccount), null));
    }
    @GetMapping(value = "/reportedAcc/cekrekening/{reportedAccount_Number}", produces = "application/json")
    public ResponseEntity<ApiResponse<GetReportedAccountAndAccountByAccountNumber>> getReportedAccountByReportedAccountId(@PathVariable String reportedAccount_Number) {
        if (reportedAccount_Number == null || reportedAccount_Number.isEmpty()) {
            ApiResponse<GetReportedAccountAndAccountByAccountNumber> response = new ApiResponse<>(false, null, "Account number source and destination can't be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (reportedAccount_Number.length() != 10) {
            ApiResponse<GetReportedAccountAndAccountByAccountNumber> response = new ApiResponse<>(false, null, "Account number must consist of 10 numbers");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Account account = accountService.getAccountByAccountNumber(reportedAccount_Number);

        if (account == null) {
            ApiResponse<GetReportedAccountAndAccountByAccountNumber> response = new ApiResponse<>(false, null, "Account with this account_number not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(new ApiResponse<>(true, reportedAccountService.getReportedAccountAndAccountByReportedAccountNumber(reportedAccount_Number), null));
    }

    @PatchMapping(value = "/reportedAcc/reports/{id}/status", produces = "application/json")
    public ResponseEntity<ApiResponse<GetReportedAccount>> updateStatus(@PathVariable Long id, @RequestBody Map<String, Integer> requestBody, @RequestHeader(name = "Authorization") String header) {
        Integer newStatus = requestBody.get("status");
        return ResponseEntity.ok(new ApiResponse<>(true, reportedAccountService.updateReportedAccountStatus(id, newStatus, JwtAuthFilter.getAdminIdFromToken(header.substring(7))), null));
    }

    @GetMapping(value = "/reportedAcc/website", produces = "application/json")
    public ResponseEntity<ApiResponse<List<GetAllReportedAccount>>> getAllReportedAccountAndReports(@RequestHeader(name = "Authorization") String header){
        JwtAuthFilter.checkAdminToken(header.substring(7));
        return ResponseEntity.ok(new ApiResponse<>(true, reportedAccountService.getAllReportedAccountAndReports(), null));
    }

    @GetMapping(value = "/reportedAcc/website/reports/{reported_id}", produces = "application/json")
    public ResponseEntity<ApiResponse<List<GetAllReportedAccountDetailReports>>> getAllReportedAccountAndReportsDetail(@PathVariable long reported_id, @RequestHeader(name = "Authorization") String header){
        JwtAuthFilter.checkAdminToken(header.substring(7));
        return ResponseEntity.ok(new ApiResponse<>(true, reportedAccountService.getAllReportedAccountDetailReports(reported_id), null));
    }

    @GetMapping("/reports/dashboard")
    public ResponseEntity<ApiResponse<GetDashboard>> getDashboardReport(@RequestHeader(name = "Authorization") String header){
        JwtAuthFilter.checkAdminToken(header.substring(7));
        return ResponseEntity.ok(new ApiResponse<>(true, reportedAccountService.getReportDashboard(),null));
    }
}
