package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.db.GetReportsAndTransactionByCustomerName;
import com.unboxit.bnichecking.entity.http.request.CreateReport;
import com.unboxit.bnichecking.entity.http.response.ApiResponse;
import com.unboxit.bnichecking.entity.http.response.CreateReportResponse;
import com.unboxit.bnichecking.entity.http.response.GetAllReports;
import com.unboxit.bnichecking.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ResponseEntity<ApiResponse<List<GetAllReports>>> getReports() {
        return ResponseEntity.ok(new ApiResponse<>(true, reportsService.getReports(), null));
    }

    @GetMapping(value = "/reports/reportsByName/{username}", produces = "application/json") //Get Resource
    public ResponseEntity<ApiResponse<List<GetReportsAndTransactionByCustomerName>>> getReportsAndTransactionByCustomerName(@PathVariable String username) {
        return ResponseEntity.ok(new ApiResponse<>(true, reportsService.getReportsAndTransactionByCustomerNames(username), null));
    }

    @PostMapping(value = "/reports") //Create Resource
    public ResponseEntity<ApiResponse<CreateReportResponse>> createReportsAndReportsAttachment(@RequestParam("transaction_id") Long TransactionId, @RequestParam("chronology") String chronology, @RequestParam("file") List<MultipartFile> files) throws IOException {
        return ResponseEntity.ok(new ApiResponse<>(true, reportsService.createReportsAndAttachment(TransactionId, chronology, files), null));
    }

}
