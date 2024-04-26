package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.http.request.CreateBlacklistTwitterUsername;
import com.unboxit.bnichecking.entity.http.response.ApiResponse;
import com.unboxit.bnichecking.model.BlacklistTwitterUsername;
import com.unboxit.bnichecking.model.TwitterReport;
import com.unboxit.bnichecking.service.BlacklistTwitterUsernameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlacklistTwitterUsernameController {
    private final BlacklistTwitterUsernameService blacklistTwitterUsernameService;

    public BlacklistTwitterUsernameController(BlacklistTwitterUsernameService blacklistTwitterUsernameService) {
        this.blacklistTwitterUsernameService = blacklistTwitterUsernameService;
    }

    @GetMapping("/blacklistTwitterUsername")
    public ResponseEntity<ApiResponse<List<BlacklistTwitterUsername>>> getAllBlacklistTwitterUsernames(){
        return ResponseEntity.ok(new ApiResponse<>(true, blacklistTwitterUsernameService.getAllBlacklistTwitterUsername(), null));
    }

    @PostMapping("/blacklistTwitterUsername")
    public ResponseEntity<ApiResponse<BlacklistTwitterUsername>> createBlacklistTwitterUsername(@RequestBody CreateBlacklistTwitterUsername newBlacklistTwitterUsername){
        BlacklistTwitterUsername savedBlacklistTwitterUsername = blacklistTwitterUsernameService.createBlacklistTwitterUsername(
                new BlacklistTwitterUsername(newBlacklistTwitterUsername.getUsername(), newBlacklistTwitterUsername.getBlocked()));
        return ResponseEntity.ok(new ApiResponse<>(true, savedBlacklistTwitterUsername, null));

    }
}
