package com.unboxit.bnichecking.seeder;

import com.unboxit.bnichecking.model.*;
import com.unboxit.bnichecking.repository.*;
import com.unboxit.bnichecking.service.AccountService;
import com.unboxit.bnichecking.service.UserService;
import com.unboxit.bnichecking.util.PasswordHasherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Autowired
    private UserService userService;

    @Autowired
    private UserJpaRepository userJpaRepository;



    @Value("${seed.data.enabled:true}")
    private boolean seedDataEnabled;

    @Value("${seed.data.csv.path:data/accounts.csv}")
    private String csvFilePath;

    @Bean
    public CommandLineRunner accountSeederCommandLineRunner() {
        return args -> {
            if (seedDataEnabled && userJpaRepository.count() == 0) {
                User user1 = new User("Sofi Shahira Khoirun Nisa", "Sofishr7", passwordHasherService.hashPassword("111111"), passwordHasherService.hashPassword("111111"));
                User user2 = new User("Renata Rizki Rafi Athallah", "rizkirafi", passwordHasherService.hashPassword("222222"), passwordHasherService.hashPassword("222222"));

                userJpaRepository.saveAll(Arrays.asList(user1, user2));

                if (seedDataEnabled && accountJpaRepository.count() == 0) {
    //                List<Account> accounts = readAccountsFromCSV();
                    Account account1 = new Account("1234567890", user1, 123000L, false);
                    Account account2 = new Account("2234567890", user2, 12300L, false);
                    Account account3 = new Account("3234567890", user2, 12300L, false);

                    accountJpaRepository.saveAll(Arrays.asList(account1, account2, account3));
                }
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
                        User user = userService.getUserByUserId(Long.parseLong(data[1]));
                        Long balance = Long.parseLong(data[2]);
                        boolean isActive = Boolean.parseBoolean(data[3]);
                        Account account = new Account(accountNumber, user, balance, isActive);
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
