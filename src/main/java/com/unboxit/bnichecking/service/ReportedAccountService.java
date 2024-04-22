package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.model.ReportedAccount;
import com.unboxit.bnichecking.repository.ReportedAccountJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ReportedAccountService {
    @Autowired
    private ReportedAccountJpaRepository reportedAccountJpaRepository;

    public ReportedAccountService(ReportedAccountJpaRepository reportedAccountJpaRepository) {
        this.reportedAccountJpaRepository = reportedAccountJpaRepository;
    }
    public List<ReportedAccount> getReportedAccount(){
        return this.reportedAccountJpaRepository.findAll();
    }

    public ReportedAccount getReportedAccountById(Long id){
        return this.reportedAccountJpaRepository.findById(id).get();
    }

    public ReportedAccount createReportedAccount(@RequestBody ReportedAccount newReportedAccount){
        return this.reportedAccountJpaRepository.save(newReportedAccount);
    }
    public ResponseEntity<String> updateReportedAccount(@PathVariable Long id, @RequestBody ReportedAccount updatedReportedAccount){
        if(this.reportedAccountJpaRepository.findById(id).isEmpty()){
            return ResponseEntity.status(400).body("Report Account not found");
        }
        updatedReportedAccount.setReportedAccountId(id);
        this.reportedAccountJpaRepository.save(updatedReportedAccount);
        return ResponseEntity.ok("Success");
    }

    public ResponseEntity<String> deleteReportedAccount(@PathVariable Long id){
        if(this.reportedAccountJpaRepository.findById(id).isEmpty()){
            return ResponseEntity.status(400).body("Report Account not found");
        }
        this.reportedAccountJpaRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
