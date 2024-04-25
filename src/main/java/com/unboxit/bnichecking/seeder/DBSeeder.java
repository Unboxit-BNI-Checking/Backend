package com.unboxit.bnichecking.seeder;

import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.model.Admins;
import com.unboxit.bnichecking.model.Transaction;
import com.unboxit.bnichecking.model.TwitterReport;
import com.unboxit.bnichecking.repository.AccountJpaRepository;
import com.unboxit.bnichecking.repository.AdminJpaRepository;
import com.unboxit.bnichecking.repository.TransactionJpaRepository;
import com.unboxit.bnichecking.repository.TwitterReportJpaRepository;
import com.unboxit.bnichecking.service.AccountService;
import com.unboxit.bnichecking.util.PasswordHasherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DBSeeder {

    @Autowired
    private AccountJpaRepository accountJpaRepository;

    @Autowired
    private TransactionJpaRepository transactionJpaRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AdminJpaRepository adminJpaRepository;

    @Autowired
    private PasswordHasherService passwordHasherService;

    @Autowired
    private TwitterReportJpaRepository twitterReportJpaRepository;



    @Value("${seed.data.enabled:true}")
    private boolean seedDataEnabled;

    @Value("${seed.data.csv.path:data/accounts.csv}")
    private String csvFilePath;

    @Bean
    public CommandLineRunner accountSeederCommandLineRunner() {
        return args -> {
            if (seedDataEnabled && accountJpaRepository.count() == 0) {
                List<Account> accounts = readAccountsFromCSV();
                accountJpaRepository.saveAll(accounts);
            }

            if (seedDataEnabled && adminJpaRepository.count() == 0) {
                Admins admin1 = new Admins("admin1", passwordHasherService.hashPassword("password1"));
                Admins admin2 = new Admins("admin2", passwordHasherService.hashPassword("password234"));

                adminJpaRepository.saveAll(Arrays.asList(admin1, admin2));
            }

            if (seedDataEnabled && transactionJpaRepository.count() == 0) {
                Account sourceAccount = accountService.getAccountByAccountId(1L);
                Account destinationAccount = accountService.getAccountByAccountId(2L);
                Transaction transaction1 = new Transaction(sourceAccount, destinationAccount, 120000L, null);
                Transaction transaction2 = new Transaction(sourceAccount, destinationAccount, 12000L, "test transfer");
                Transaction transaction3 = new Transaction(sourceAccount, destinationAccount, 20000L, "test lagi");

                transactionJpaRepository.saveAll(Arrays.asList(transaction1, transaction2, transaction3));
            }

            if (seedDataEnabled && twitterReportJpaRepository.count() == 0) {
                Account a1 = accountService.getAccountByAccountId(1L);
                TwitterReport twitterReport1 = new TwitterReport(LocalDateTime.now(), "username1", "link1", a1, null);
                TwitterReport twitterReport2 = new TwitterReport(LocalDateTime.now().minusDays(20), "username2", "link2", a1, null);
                TwitterReport twitterReport3 = new TwitterReport(LocalDateTime.now().minusDays(20), "username2", "link3", a1, null);
                TwitterReport twitterReport4 = new TwitterReport(LocalDateTime.now().minusDays(20), "username2", "link4", a1, null);

                twitterReportJpaRepository.saveAll(Arrays.asList(twitterReport1, twitterReport2, twitterReport3, twitterReport4));

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