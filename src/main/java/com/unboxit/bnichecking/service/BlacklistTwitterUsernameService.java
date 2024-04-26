package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.model.BlacklistTwitterUsername;
import com.unboxit.bnichecking.repository.BlacklistTwitterUsernameJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlacklistTwitterUsernameService {
    @Autowired
    private BlacklistTwitterUsernameJpaRepository repository;

    public List<BlacklistTwitterUsername> getAllBlacklistTwitterUsername(){
        return repository.findAll();
    }

    public BlacklistTwitterUsername createBlacklistTwitterUsername (BlacklistTwitterUsername blacklistTwitterUsername){
        return repository.save(blacklistTwitterUsername);
    }
}
