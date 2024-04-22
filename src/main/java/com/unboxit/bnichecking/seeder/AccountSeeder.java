package com.unboxit.bnichecking.seeder;

import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.repository.AccountJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class AccountSeeder {

    @Autowired
    private AccountJpaRepository accountJpaRepository;

    @Value("${seed.data.enabled:true}")
    private boolean seedDataEnabled;

    @Bean
    public CommandLineRunner seedDatabase() {
        return args -> {
            if (seedDataEnabled && accountJpaRepository.count() == 0) {
                Account user1 = new Account("1234567890", "john doe", 120000L, false);
                Account user2 = new Account("2234567890", "suzie", 10000L, false);

                accountJpaRepository.saveAll(Arrays.asList(user1, user2));
            }
        };
    }
}
