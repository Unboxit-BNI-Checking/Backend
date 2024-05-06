package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.http.request.LoginUser;
import com.unboxit.bnichecking.entity.http.response.ApiResponse;
import com.unboxit.bnichecking.entity.http.response.GetAccountNumberByUserId;
import com.unboxit.bnichecking.entity.http.response.LoginUserResponse;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.model.User;
import com.unboxit.bnichecking.service.UserService;

import com.unboxit.bnichecking.entity.http.request.CreateUser;
import com.unboxit.bnichecking.util.JwtAuthFilter;
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
public class UserController {
    private final UserService userService;
    private PasswordHasherService passwordHasherService;
    @Autowired
    public UserController(UserService userService, PasswordHasherService passwordHasherService) {
        this.userService = userService;
        this.passwordHasherService = passwordHasherService;
    }

    @GetMapping(value = "/users", produces = "application/json") //Get Resource
    public ResponseEntity<ApiResponse<List<User>>> getAllUser() {
        return ResponseEntity.ok(new ApiResponse<>(true, userService.getAllUser(), null));
    }

    @GetMapping(value = "/users/user_id", produces = "application/json") //Get Resource
    public ResponseEntity<ApiResponse<User>> getUserByUserId(@RequestHeader(name = "Authorization") String header) {
        return ResponseEntity.ok(new ApiResponse<>(true, userService.getUserByUserId(JwtAuthFilter.getUserIdFromToken(header.substring(7))), null));
    }

    @GetMapping(value = "/users/accountNumber", produces = "application/json") //Get Resource
    public ResponseEntity<ApiResponse<GetAccountNumberByUserId>> getAccountNumberByUserId(@RequestHeader(name = "Authorization") String header) {
        return ResponseEntity.ok(new ApiResponse<>(true, userService.getAccountNumberByUserId(JwtAuthFilter.getUserIdFromToken(header.substring(7))), null));
    }
    
    @PostMapping("/users/login")
    public ResponseEntity<ApiResponse<LoginUserResponse>> loginUser(@RequestBody LoginUser loginUser){
        if(loginUser.getUsername()!=null && loginUser.getMpin()!=null){
            User user= userService.findUserByUsername(loginUser.getUsername());
            if(user!=null){
                boolean isCorrect = passwordHasherService.checkPassword(loginUser.getMpin(), user.getHashedMpin());
                if(isCorrect){
                    String tokenUser = Jwts.builder()
                            .subject(user.getUsername())
                            .claim("role", "user")
                            .claim("user_id", user.getUserId())
                            .signWith(SignatureAlgorithm.HS256, "secretkeyasdafnajndnsakmdkamfkmakekasmdkammkfskamkamkdmasmdkmaskdmasmdmasmdka")
                            .issuedAt(Date.from(Instant.now()))
                            .expiration(Date.from(Instant.now().plus(365, ChronoUnit.DAYS)))
                            .compact();
                    return ResponseEntity.ok(new ApiResponse<>(true,new LoginUserResponse(tokenUser),null));
                }else {
                    ApiResponse<LoginUserResponse> response = new ApiResponse<>(false, null, "Username or Mpin incorrect");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

                }
            }
        }
        ApiResponse<LoginUserResponse> response = new ApiResponse<>(false, null, "Username or Mpin incorrect");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping("/users")
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody CreateUser newUser) {
        String hashPassword = passwordHasherService.hashPassword(newUser.getPassword());
        String hashMpin= passwordHasherService.hashPassword(newUser.getMpin());

        User savedUser = userService.createUser(new User(newUser.getCustomerName(), newUser.getUsername(), hashPassword, hashMpin));
        return ResponseEntity.ok(new ApiResponse<>(true, savedUser, null));
    }

}
