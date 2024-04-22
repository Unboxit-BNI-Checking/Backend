package com.unboxit.bnichecking.seeder;

import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.repository.AccountJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class AccountSeeder {

    @Autowired
    private AccountJpaRepository accountJpaRepository;

    @Value("${seed.data.enabled:true}")
    private boolean seedDataEnabled;

    @Value("${seed.data.csv.path:data/accounts.csv}")
    private String csvFilePath;

    @Bean
    public CommandLineRunner seedDatabase() {
        return args -> {
            if (seedDataEnabled && accountJpaRepository.count() == 0) {
                List<Account> accounts = readAccountsFromCSV();
                accountJpaRepository.saveAll(accounts);
            }
        };
    }

    private List<Account> readAccountsFromCSV() {
        List<Account> accounts = new ArrayList<>();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(csvFilePath);
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                boolean skipHeader = true;
                while ((line = reader.readLine()) != null) {
                    if (skipHeader) {
                        skipHeader = false;
                        continue;
                    }

                    String[] data = line.split(",");
                    if (data.length == 4) {
                        String accountNumber = data[0];
                        String name = data[1];
                        Long balance = Long.parseLong(data[2]);
                        boolean isActive = Boolean.parseBoolean(data[3]);
                        Account account = new Account(accountNumber, name, balance, isActive);
                        accounts.add(account);
                    } else {
                        System.err.println("Invalid data format in CSV file: " + line);
                    }
                }
                reader.close();
            } else {
                System.err.println("CSV file not found at path: " + csvFilePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }
}
