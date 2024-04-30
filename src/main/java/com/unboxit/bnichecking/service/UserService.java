package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.model.User;
import com.unboxit.bnichecking.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserJpaRepository userJpaRepository;

    public List<User> getAllUsers(){
        return userJpaRepository.findAll();
    }

    public User getUserByUserId(long user_id){
        return userJpaRepository.findUserByUserId(user_id);
    }
}
