package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.model.Reports;
import com.unboxit.bnichecking.repository.ReportsJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ReportsService {
    @Autowired
    private ReportsJpaRepository reportsRepository;

    public ReportsService(ReportsJpaRepository reportsRepository) {
        this.reportsRepository = reportsRepository;
    }
    public List<Reports> getReports(){
        return this.reportsRepository.findAll();
    }

    public Reports getReportsById(Long id){
        return this.reportsRepository.findById(id).get();
    }

    public Reports createReports(@RequestBody Reports newReports){
        return this.reportsRepository.save(newReports);
    }

    public ResponseEntity<String> updateReports(@PathVariable Long id, @RequestBody Reports updatedReports){
        if(this.reportsRepository.findById(id).isEmpty()){
            return ResponseEntity.status(400).body("Reports not found");
        }
        updatedReports.setReportId(id);
        this.reportsRepository.save(updatedReports);
        return ResponseEntity.ok("Success");
    }

    public ResponseEntity<String> deleteReports(@PathVariable Long id){
        if(this.reportsRepository.findById(id).isEmpty()){
            return ResponseEntity.status(400).body("Reports not found");
        }
        this.reportsRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
