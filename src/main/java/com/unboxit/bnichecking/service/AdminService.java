package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.model.Admins;
import com.unboxit.bnichecking.repository.AdminJpaRepository;
import com.unboxit.bnichecking.util.PasswordHasherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminJpaRepository repository;

    public List<Admins> getAllAdmin(){
        return repository.findAll();
    }

    public Admins findAdminByUsername(String username){
        return repository.findAdminsByUsername(username);
    }


}
