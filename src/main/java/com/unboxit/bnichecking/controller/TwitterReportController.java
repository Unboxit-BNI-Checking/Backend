package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.model.Favourite;
import com.unboxit.bnichecking.model.TwitterReport;
import com.unboxit.bnichecking.repository.TwitterReportJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TwitterReportController {
    @Autowired
    private TwitterReportJpaRepository repository;

    @GetMapping("/twitterReports")
    public List<TwitterReport> getAllTwitterReport(){
        return repository.findAll();
    }

    @PostMapping("/twitterReports")
    public TwitterReport createFavourite(@RequestBody TwitterReport twitterReport){
        return repository.save(twitterReport);
    }


}
