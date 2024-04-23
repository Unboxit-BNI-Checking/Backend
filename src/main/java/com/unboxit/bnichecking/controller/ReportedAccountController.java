package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.model.ReportedAccount;
import com.unboxit.bnichecking.service.ReportedAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReportedAccountController {
    private final ReportedAccountService reportedAccountService;

    @Autowired
    public ReportedAccountController(ReportedAccountService reportedAccountService) {
        this.reportedAccountService = reportedAccountService;
    }

    @GetMapping(value = "/reportedAcc", produces = "application/json") //Get Resource
    public List<ReportedAccount> getReportedAccount(){
        return reportedAccountService.getReportedAccount();
    }

    @PostMapping(value = "/reportedAcc", consumes = "application/json", produces = "application/json") //Create Resource
    public ReportedAccount createReportedAccount(@RequestBody ReportedAccount newReportedAccount){
        return reportedAccountService.createReportedAccount(newReportedAccount);
    }

    @GetMapping(value = "/reportedAcc/{id}", produces = "application/json") //Get Resource
    public ReportedAccount getReportedAccountById(@PathVariable Long id){
        return reportedAccountService.getReportedAccountById(id);
    }

    @PutMapping(value = "/reportedAcc/{id}", produces = "application/json") //Update Resource
    public ResponseEntity<String> updateReportedAccount(@PathVariable Long id, @RequestBody ReportedAccount updatedReportedAccount){
        return reportedAccountService.updateReportedAccount(id, updatedReportedAccount);
    }

    @DeleteMapping(value = "/reportedAcc/{id}", produces = "application/json") //Delete Resource
    public ResponseEntity<String> deleteReportedAccount(@PathVariable Long id){
        return reportedAccountService.deleteReportedAccount(id);
    }
}
