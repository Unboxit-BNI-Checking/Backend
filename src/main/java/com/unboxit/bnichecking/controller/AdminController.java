package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.http.response.ApiResponse;
import com.unboxit.bnichecking.model.Admins;
import com.unboxit.bnichecking.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    
    @GetMapping("/admins")
    public ResponseEntity<ApiResponse<List<Admins>>> getAllAdmin(){
        return ResponseEntity.ok(new ApiResponse<>(true, adminService.getAllAdmin(), null));
    }

    @GetMapping("/admins/login")
    public ResponseEntity<ApiResponse<List<Admins>>> loginAdmin(){
        return ResponseEntity.ok(new ApiResponse<>(true, adminService.getAllAdmin(), null));
    }
}
