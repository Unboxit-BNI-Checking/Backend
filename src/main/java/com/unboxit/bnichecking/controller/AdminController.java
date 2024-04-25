package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.http.request.LoginAdmin;
import com.unboxit.bnichecking.entity.http.response.ApiResponse;
import com.unboxit.bnichecking.model.Admins;
import com.unboxit.bnichecking.service.AdminService;
import com.unboxit.bnichecking.util.PasswordHasherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {

    private final AdminService adminService;
    @Autowired
    private PasswordHasherService passwordHasherService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    
    @GetMapping("/admins")
    public ResponseEntity<ApiResponse<List<Admins>>> getAllAdmin(){
        return ResponseEntity.ok(new ApiResponse<>(true, adminService.getAllAdmin(), null));
    }

    @GetMapping("/admins/login")
    public ResponseEntity<ApiResponse<Boolean>> loginAdm(@RequestParam (required = true) String username, @RequestParam (required = true) String password){

        return ResponseEntity.ok(new ApiResponse<>(true,adminService.checkLoginAdmin(username, password), null));
    }

    @PostMapping("/admins/login")
    public ResponseEntity<ApiResponse<Boolean>> loginAdm(@RequestBody LoginAdmin loginAdmin){

        boolean isCorrect =adminService.checkLoginAdmin(loginAdmin.getUsername(), loginAdmin.getPassword());
        if (isCorrect) {
            return ResponseEntity.ok(new ApiResponse<>(true, isCorrect, "Login successful"));
        } else {
            ApiResponse<Boolean> response = new ApiResponse<>(false, null, "Username or password incorrect");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/admins/ceklogn")
    public ResponseEntity<ApiResponse<Boolean>> loginAdmn (@RequestBody LoginAdmin loginAdmin){
        if(loginAdmin.getUsername()!=null && loginAdmin.getPassword()!=null){
            Admins admins = adminService.findAdminByUsername(loginAdmin.getUsername());
            if(admins !=null){
                boolean isCorrct =passwordHasherService.checkPassword(loginAdmin.getPassword(), admins.getHashedPassword());
                if (isCorrct) {
                    return ResponseEntity.ok(new ApiResponse<>(true, isCorrct , "Login successful"));
                } else {
                    ApiResponse<Boolean> response = new ApiResponse<>(false, null, "Username or password incorrect");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }
            }
        }

        ApiResponse<Boolean> response = new ApiResponse<>(false, null, "Username or password incorrect");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
