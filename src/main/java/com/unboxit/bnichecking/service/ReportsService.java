package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.entity.http.response.GetAllReports;
import com.unboxit.bnichecking.model.Reports;
import com.unboxit.bnichecking.repository.ReportsJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ReportsService {
    @Autowired
    private ReportsJpaRepository reportsRepository;

    public ReportsService(ReportsJpaRepository reportsRepository) {
        this.reportsRepository = reportsRepository;
    }
    public List<GetAllReports> getReports(){
        return this.reportsRepository.findAll();
    }

    public Reports getReportsById(Long id){
        return this.reportsRepository.findById(id).get();
    }

    public Reports createReports(@RequestBody Reports newReports){
        return this.reportsRepository.save(newReports);
    }
}
