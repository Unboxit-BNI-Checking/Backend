package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.model.User;
import com.unboxit.bnichecking.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserJpaRepository repository;
    public User getUserByUserId(Long userId) {
        return repository.findUserByUserId(userId);
    }

    public User createUser(User newUser){
        return repository.save(newUser);
    }
    public List<User> getAllUser(){
        return repository.findAll();
    }

    public User findUserByUsername(String username){
        return repository.findUserByUsername(username);
    }
}
