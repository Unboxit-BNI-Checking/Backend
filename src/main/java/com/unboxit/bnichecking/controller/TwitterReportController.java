package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.http.request.CreateTwitterReport;
import com.unboxit.bnichecking.entity.http.response.ApiResponse;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.model.TwitterReport;
import com.unboxit.bnichecking.service.AccountService;
import com.unboxit.bnichecking.service.TwitterReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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
    @GetMapping("/twitter-reports")
    public ResponseEntity<ApiResponse<List<TwitterReport>>> getAllTwitterReportByQueris(@RequestParam(name = "from_date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
                                                                                        @RequestParam(name = "include_deleted", required = false, defaultValue = "false") boolean includeDeleted,
                                                                                        @RequestParam(name = "twitter_username", required = false) String twitterUsername){


        return ResponseEntity.ok(new ApiResponse<>(true, twitterReportService.getAllTwitterReportByQueries(fromDate, twitterUsername, includeDeleted),null));
    }

    @PostMapping("/twitter-reports")
    public ResponseEntity<ApiResponse<TwitterReport>> createTwitterReport(@RequestBody CreateTwitterReport newTwitterReport){
        Account reportedAccount = accountService.getAccountByAccountNumber(newTwitterReport.getReportedAccountNumber());
        if(reportedAccount == null){
            ApiResponse<TwitterReport> response = new ApiResponse<>(false, null, "Reported account number is invalid!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        TwitterReport savedTwitterReport = twitterReportService.createTwitterReport(new TwitterReport(newTwitterReport.getPostDate(), newTwitterReport.getTwitterUsername(), newTwitterReport.getTweetLink(),reportedAccount, null));
        return ResponseEntity.ok(new ApiResponse<>(true, savedTwitterReport, null));
    }

    @GetMapping("/twitter-reports/{reported_account_number}")
    public List<TwitterReport> getAllTwitterReportByAccountNumber(@PathVariable String reported_account_number){
        return twitterReportService.getAllTwitterReportByAccountNumber(reported_account_number);
    }


}
