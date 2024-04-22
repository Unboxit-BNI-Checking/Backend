package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.model.TwitterReport;
import com.unboxit.bnichecking.repository.TwitterReportJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
