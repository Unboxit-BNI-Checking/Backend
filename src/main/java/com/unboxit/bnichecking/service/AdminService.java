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

        return true;
//        if (user != null) {
//            if (passwordHasherService.checkPassword(loginDTO.getPassword(), user.getPassword())) {
//                return "Login successful!";
//            } else {
//                return "Invalid password!";
//            }
//        } else {
//            return "User not found!";
//        }
    }

}
