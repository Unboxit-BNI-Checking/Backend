package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.model.TwitterReport;
import com.unboxit.bnichecking.repository.TwitterReportJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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
        List<TwitterReport> twitterReports;

        if (fromDate != null) {
            if (twitterUsername != null && !twitterUsername.isEmpty()) {
                twitterReports = repository.findByCreatedAtAfterAndDeletedAtIsNullAndTwitterUsername(fromDate, twitterUsername);
            } else {
                twitterReports = repository.findByCreatedAtAfterAndDeletedAtIsNull(fromDate);
            }
        } else if (includeDeleted) {
            twitterReports = repository.findAllByDeletedAtNotNull();
        } else if (twitterUsername != null && !twitterUsername.isEmpty()) {
            twitterReports = repository.findAllByTwitterUsername(twitterUsername);
        } else {
            twitterReports = repository.findAll();
        }


        return twitterReports;

    }
}
