package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.http.response.ApiResponse;
import com.unboxit.bnichecking.model.TwitterReport;
import com.unboxit.bnichecking.service.AccountService;
import com.unboxit.bnichecking.service.TwitterReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TwitterReportController {

    private final TwitterReportService twitterReportService;
    private final AccountService accountService;

    @Autowired
    public TwitterReportController(TwitterReportService twitterReportService, AccountService accountService) {
        this.twitterReportService = twitterReportService;
        this.accountService = accountService;
    }

    @GetMapping("/twitterReports")
    public ResponseEntity<ApiResponse<List<TwitterReport>>> getAllTwitterReport(){
        return ResponseEntity.ok(new ApiResponse<>(true, twitterReportService.getAllTwitterReport(), null));
    }



}
