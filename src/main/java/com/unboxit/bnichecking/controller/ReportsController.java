package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.model.Reports;
import com.unboxit.bnichecking.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReportsController {
    private final ReportsService reportsService;

    @Autowired
    public ReportsController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @GetMapping(value = "/reports", produces = "application/json") //Get Resource
    public List<Reports> getReports(){
        return reportsService.getReports();
    }

    @PostMapping(value = "/reports", consumes = "application/json", produces = "application/json") //Create Resource
    public Reports createReports(@RequestBody Reports newReports){
        return reportsService.createReports(newReports);
    }

    @GetMapping(value = "/reports/{id}", produces = "application/json") //Get Resource
    public Reports getReportsById(@PathVariable Long id){
        return reportsService.getReportsById(id);
    }

    @PutMapping(value = "/reports/{id}", produces = "application/json") //Update Resource
    public ResponseEntity<String> updateReports(@PathVariable Long id, @RequestBody Reports updatedReports){
        return reportsService.updateReports(id, updatedReports);
    }

    @DeleteMapping(value = "/reports/{id}", produces = "application/json") //Delete Resource
    public ResponseEntity<String> deleteReports(@PathVariable Long id){
        return reportsService.deleteReports(id);
    }
}
