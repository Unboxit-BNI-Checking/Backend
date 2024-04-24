package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.http.response.GetAllReports;
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
    public List<GetAllReports> getReports(){
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

}
