package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.http.request.LoginAdmin;
import com.unboxit.bnichecking.entity.http.request.LoginUser;
import com.unboxit.bnichecking.entity.http.response.ApiResponse;
import com.unboxit.bnichecking.entity.http.response.GetAllUser;
import com.unboxit.bnichecking.entity.http.response.LoginAdminResponse;
import com.unboxit.bnichecking.entity.http.response.LoginUserResponse;
import com.unboxit.bnichecking.model.User;
import com.unboxit.bnichecking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user", produces = "application/json") //Get Resource
    public ResponseEntity<ApiResponse<List<User>>> getAllUser() {
        return ResponseEntity.ok(new ApiResponse<>(true, userService.getAllUsers(), null));
    }

    @GetMapping(value = "/user/{user_id}", produces = "application/json") //Get Resource
    public ResponseEntity<ApiResponse<User>> getUserByUserId(@PathVariable long user_id) {
        return ResponseEntity.ok(new ApiResponse<>(true, userService.getUserByUserId(user_id), null));
    }

    @PostMapping("/user/login")
    public ResponseEntity<ApiResponse<LoginUserResponse>> loginUser (@RequestBody LoginUser loginUser){
        ApiResponse<LoginUserResponse> response = new ApiResponse<>(false, null, "Username or password incorrect");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
