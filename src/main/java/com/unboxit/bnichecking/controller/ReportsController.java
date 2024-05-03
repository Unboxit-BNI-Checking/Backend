package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.db.GetReportsAndTransactionByCustomerName;
import com.unboxit.bnichecking.entity.http.request.CreateReportRequest;
import com.unboxit.bnichecking.entity.http.response.*;
import com.unboxit.bnichecking.service.ReportsService;
import com.unboxit.bnichecking.service.UserService;
import com.unboxit.bnichecking.util.JwtAuthFilter;
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
    private final UserService userService;

    @Autowired
    public ReportsController(ReportsService reportsService, UserService userService) {
        this.reportsService = reportsService;
        this.userService = userService;
    }

    @GetMapping(value = "/reports", produces = "application/json") //Get Resource
    public ResponseEntity<ApiResponse<List<GetAllReports>>> getReports() {
        return ResponseEntity.ok(new ApiResponse<>(true, reportsService.getReports(), null));
    }

    @GetMapping(value = "/reports/me", produces = "application/json") //Get Resource
    public ResponseEntity<ApiResponse<List<GetReportsAndTransactionByCustomerName>>> getReportsAndTransactionByCustomerName(@RequestHeader(name = "Authorization") String header) {
        String username = userService.getUserByUserId(JwtAuthFilter.getUserIdFromToken(header.substring(7))).getUsername();
        return ResponseEntity.ok(new ApiResponse<>(true, reportsService.getReportsAndTransactionByCustomerNames(username), null));
    }

    @PostMapping(value = "/reports") //Create Resource
    public ResponseEntity<ApiResponse<CreateReportResponse>> createReportsAndReportsAttachment(@ModelAttribute CreateReportRequest request) throws IOException {
        return ResponseEntity.ok(new ApiResponse<>(true, reportsService.createReportsAndAttachment(request.getTransactionId(), request.getChronology(), request.getFiles()), null));
    }

    @GetMapping(value = "/reports/total-reports-by-month")
    public ResponseEntity<ApiResponse<List<GetTotalReportCompleted>>> getTotalReportsByMonth() {
        return ResponseEntity.ok(new ApiResponse<>(true, reportsService.getTotalReportCompletedByMonth(),null));
    }

}
