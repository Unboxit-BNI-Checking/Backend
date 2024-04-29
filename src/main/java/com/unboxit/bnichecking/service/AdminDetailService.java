package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.model.Admins;
import com.unboxit.bnichecking.repository.AdminJpaRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailService implements UserDetailsService {
    private final AdminJpaRepository adminRepository;

    public AdminDetailService(AdminJpaRepository adminRepository, AdminJpaRepository adminRepository1) {
        this.adminRepository = adminRepository1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admins admin = adminRepository.findAdminsByUsername(username);
        return org.springframework.security.core.userdetails.User.builder()
                .username(admin.getUsername())
                .password(admin.getHashedPassword())
                .build();
    }
}
