package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.model.Admins;
import com.unboxit.bnichecking.model.User;
import com.unboxit.bnichecking.repository.AdminJpaRepository;
import com.unboxit.bnichecking.repository.UserJpaRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailService implements UserDetailsService {
    private final AdminJpaRepository adminRepository;
    private final UserJpaRepository userRepository;

    public AdminDetailService(AdminJpaRepository adminRepository, AdminJpaRepository adminRepository1, UserJpaRepository userRepository) {
        this.adminRepository = adminRepository1;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        String uname;
        String upass;
        if (user != null) {
            uname = user.getUsername();
            upass = user.getHashedMpin();
        } else {
            Admins admin = adminRepository.findAdminsByUsername(username);
            uname = admin.getUsername();
            upass = admin.getHashedPassword();
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(uname)
                .password(upass)
                .build();
    }
}
