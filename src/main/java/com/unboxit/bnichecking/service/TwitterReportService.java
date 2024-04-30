package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.model.TwitterReport;
import com.unboxit.bnichecking.repository.TwitterReportJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TwitterReportService {
    @Autowired
    private TwitterReportJpaRepository repository;

    public List<TwitterReport> getAllTwitterReport(){
        return repository.findAll();
    }

    public TwitterReport createTwitterReport(TwitterReport twitterReport){
        return repository.save(twitterReport);
    }

    public List<TwitterReport> getAllTwitterReportByAccountNumber(String accountNumber){
        return repository.findByAccountNumber(accountNumber);
    }

    public List<TwitterReport> getAllTwitterReportByQueries (LocalDateTime fromDate, String twitterUsername, Boolean includeDeleted){

        if (fromDate != null) {
            return repository.findTwitterReportByQueries(Timestamp.valueOf(fromDate), twitterUsername, includeDeleted);
        }
        return repository.findTwitterReportByQueries(null, twitterUsername, includeDeleted);
    }
}
