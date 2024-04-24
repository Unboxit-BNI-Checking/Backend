package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.http.request.CreateReportedAccount;
import com.unboxit.bnichecking.entity.http.response.ApiResponse;
import com.unboxit.bnichecking.entity.http.response.GetAllReportedAccount;
import com.unboxit.bnichecking.model.ReportedAccount;
import com.unboxit.bnichecking.service.AccountService;
import com.unboxit.bnichecking.service.ReportedAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ApiResponse<List<GetAllReportedAccount>>> getAllTransaction(){
         return ResponseEntity.ok(new ApiResponse<>(true, reportedAccountService.getReportedAccount(), null));
    }

    @GetMapping(value = "/reportedAcc/{reportedAccount_Id}", produces = "application/json")
    public ResponseEntity<ApiResponse<List<GetAllReportedAccount>>> getReportedAccountById(@PathVariable long reportedAccount_Id){
        return ResponseEntity.ok(new ApiResponse<>(true, reportedAccountService.getReportedAccountById(reportedAccount_Id), null));
    }

    @GetMapping(value = "/reportedAcc/account/{reported_account_number}", produces = "application/json")
    public ResponseEntity<ApiResponse<List<GetAllReportedAccount>>> getReportedAccountByReportedAccountNumber(@PathVariable String reported_account_number){
        return ResponseEntity.ok(new ApiResponse<>(true, reportedAccountService.getReportedAccountByReportedAccountNumber(reported_account_number), null));
    }

    @PostMapping(value = "/reportedAcc", consumes = "application/json", produces = "application/json") //Create Resource
    public ResponseEntity<ApiResponse<ReportedAccount>> createReportedAccount(@RequestBody CreateReportedAccount newReportedAccount){
        ReportedAccount createReportedAccount = reportedAccountService.createReportedAccount(new ReportedAccount(
                accountService.getAccountByAccountNumber(newReportedAccount.getReportedAccountNumber()),
                newReportedAccount.getStatus()));
        ApiResponse<ReportedAccount> response = new ApiResponse<>(true, createReportedAccount, null);
        return ResponseEntity.ok(response);
    }
}
