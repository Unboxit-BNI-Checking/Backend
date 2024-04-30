package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.http.request.LoginAdmin;
import com.unboxit.bnichecking.entity.http.response.ApiResponse;
import com.unboxit.bnichecking.entity.http.response.LoginAdminResponse;
import com.unboxit.bnichecking.model.Admins;
import com.unboxit.bnichecking.service.AdminService;
import com.unboxit.bnichecking.util.PasswordHasherService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
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
    public ResponseEntity<ApiResponse<List<Admins>>> getAllAdmin() {
        return ResponseEntity.ok(new ApiResponse<>(true, adminService.getAllAdmin(), null));
    }

    @PostMapping("/admins/login")
    public ResponseEntity<ApiResponse<LoginAdminResponse>> loginAdmn (@RequestBody LoginAdmin loginAdmin){
        if(loginAdmin.getUsername()!=null && loginAdmin.getPassword()!=null){
            Admins admins = adminService.findAdminByUsername(loginAdmin.getUsername());
            if(admins !=null){
                boolean isCorrct =passwordHasherService.checkPassword(loginAdmin.getPassword(), admins.getHashedPassword());
                if (isCorrct) {
//                    return ResponseEntity.ok(new ApiResponse<>(true, isCorrct , null));

                    String token = Jwts.builder()
                            .subject(admins.getUsername())
                            .claim("role", "admin")
                            .signWith(SignatureAlgorithm.HS256, "secretkeyasdafnajndnsakmdkamfkmakekasmdkammkfskamkamkdmasmdkmaskdmasmdmasmdka")
                            .issuedAt(Date.from(Instant.now()))
                            .expiration(Date.from(Instant.now().plus(365, ChronoUnit.DAYS)))
                            .compact();

                    return ResponseEntity.ok(new ApiResponse<>(true, new LoginAdminResponse(token) , null));
                } else {
                    ApiResponse<LoginAdminResponse> response = new ApiResponse<>(false, null, "Username or password incorrect");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }
            }
        }

        ApiResponse<LoginAdminResponse> response = new ApiResponse<>(false, null, "Username or password incorrect");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
