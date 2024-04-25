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

    @Autowired
    private PasswordHasherService passwordHasherService;

    public List<Admins> getAllAdmin(){
        return repository.findAll();
    }

    public Boolean checkLoginAdmin(String username, String password){
        Admins admins = repository.findAdminsByUsername(username);

        if (admins != null) {
            if (passwordHasherService.checkPassword(password, admins.getHashedPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Admins findAdminByUsername(String username){
        return repository.findAdminsByUsername(username);
    }


}
