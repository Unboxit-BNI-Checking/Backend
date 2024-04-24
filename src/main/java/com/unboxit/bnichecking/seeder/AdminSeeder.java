package com.unboxit.bnichecking.seeder;


import com.unboxit.bnichecking.model.Admins;
import com.unboxit.bnichecking.repository.AdminJpaRepository;
import com.unboxit.bnichecking.util.PasswordHasherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class AdminSeeder {

    @Autowired
    private AdminJpaRepository adminJpaRepository;

    @Autowired
    private PasswordHasherService passwordHasherService;

    @Value("${seed.data.enabled:true}")
    private boolean seedDataEnabled;

    @Bean
    public CommandLineRunner adminSeederCommandLineRunner() {
        return args -> {
            if (seedDataEnabled && adminJpaRepository.count() == 0) {
                Admins admin1 = new Admins("admin1", passwordHasherService.hashPassword("password1"));
                Admins admin2 = new Admins("admin2", passwordHasherService.hashPassword("password234"));

                adminJpaRepository.saveAll(Arrays.asList(admin1, admin2));
            }
        };
    }
}
