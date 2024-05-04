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
                User user1 = new User("Sofi Shahira Khoirun Nisa", "sofishr7", passwordHasherService.hashPassword("111111"), passwordHasherService.hashPassword("111111"));
                User user2 = new User("Renata Rizki Rafi Athallah", "rizkirafi", passwordHasherService.hashPassword("222222"), passwordHasherService.hashPassword("222222"));
                User user3 = new User("Jonathan Richard Sugandhi", "richiesugan", passwordHasherService.hashPassword("333333"), passwordHasherService.hashPassword("333333"));
                User user4 = new User("Muhamad Dani Setiawan", "danti01", passwordHasherService.hashPassword("444444"), passwordHasherService.hashPassword("444444"));
                User user5 = new User("Tiansi Ade Bora", "danti02", passwordHasherService.hashPassword("555555"), passwordHasherService.hashPassword("555555"));
                User user6 = new User("Muhammad Rasidan Haikal", "haikal69", passwordHasherService.hashPassword("666666"), passwordHasherService.hashPassword("666666"));
                User user7 = new User("Nabila Rizqa Damayanti", "nabir96", passwordHasherService.hashPassword("777777"), passwordHasherService.hashPassword("777777"));
                User user8 = new User("Nurleila Rahmawati Sulistyo", "raffly", passwordHasherService.hashPassword("888888"), passwordHasherService.hashPassword("888888"));
                User user9 = new User("Amelia Qatrunnada", "ameliaqat", passwordHasherService.hashPassword("999999"), passwordHasherService.hashPassword("999999"));

                userJpaRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9));

                if (seedDataEnabled && accountJpaRepository.count() == 0) {
                    Account account1 = new Account("1813111111", user1, 10000000L, false);
                    Account account2 = new Account("1813111112", user1, 1233000L, false);
                    Account account3 = new Account("1813111113", user1, 120000L, true);
                    Account account4 = new Account("1813222221", user2, 11000000L, false);
                    Account account5 = new Account("1813222222", user2, 2542213L, false);
                    Account account6 = new Account("1813222223", user2, 143700L, true);
                    Account account7 = new Account("1813333331", user3, 12000000L, false);
                    Account account8 = new Account("1813333332", user3, 1212300L, false);
                    Account account9 = new Account("1813333333", user3, 143300L,    true);
                    Account account10 = new Account("1813444441", user4, 13000000L, false);
                    Account account11 = new Account("1813444442", user4, 1983000L, false);
                    Account account12 = new Account("1813444443", user4, 167380L, false);
                    Account account13 = new Account("1813555551", user5, 14000000L, false);
                    Account account14 = new Account("1813555552", user5, 2378219L, false);
                    Account account15 = new Account("1813555553", user5, 479181L, false);
                    Account account16 = new Account("1813666661", user6, 15000000L, false);
                    Account account17 = new Account("1813666662", user6, 3721972L, false);
                    Account account18 = new Account("1813666663", user6, 382112L, false);
                    Account account19 = new Account("1813777771", user7, 16000000L, false);
                    Account account20 = new Account("1813777772", user7, 6362712L, false);
                    Account account21 = new Account("1813777773", user7, 890741L, false);
                    Account account22 = new Account("1813888881", user8, 17000000L, false);
                    Account account23 = new Account("1813888882", user8, 3748102L, false);
                    Account account24 = new Account("1813888883", user8, 567431L, false);
                    Account account25 = new Account("1813999991", user9, 18000000L, false);
                    Account account26 = new Account("1813999992", user9, 6374182L, false);
                    Account account27 = new Account("1813999993", user9, 236700L, false);

                    accountJpaRepository.saveAll(Arrays.asList(account1, account2, account3, account4, account5, account6, account7, account8, account9, account10, account11, account12, account13, account14, account15, account16, account17, account18, account19, account20, account21, account22, account23, account24, account25, account26, account27));
                }
            }

            if (seedDataEnabled && adminJpaRepository.count() == 0) {
                Admins admin1 = new Admins("admin1", passwordHasherService.hashPassword("password1"));
                Admins admin2 = new Admins("admin2", passwordHasherService.hashPassword("password234"));

                adminJpaRepository.saveAll(Arrays.asList(admin1, admin2));
            }

            if (seedDataEnabled && transactionJpaRepository.count() == 0) {
                Transaction transaction1 = new Transaction(accountService.getAccountByAccountId(1L), accountService.getAccountByAccountId(4L), 120000L, "Transfer");
                Transaction transaction2 = new Transaction(accountService.getAccountByAccountId(1L), accountService.getAccountByAccountId(4L), 12000L, "Transfer");
                Transaction transaction3 = new Transaction(accountService.getAccountByAccountId(1L), accountService.getAccountByAccountId(4L), 20000L, "Transfer");
                Transaction transaction4 = new Transaction(accountService.getAccountByAccountId(1L), accountService.getAccountByAccountId(4L), 120000L, "Transfer");
                Transaction transaction5 = new Transaction(accountService.getAccountByAccountId(1L), accountService.getAccountByAccountId(4L), 12000L, "Transfer");
                Transaction transaction6 = new Transaction(accountService.getAccountByAccountId(1L), accountService.getAccountByAccountId(4L), 20000L, "Transfer");
                Transaction transaction7 = new Transaction(accountService.getAccountByAccountId(1L), accountService.getAccountByAccountId(4L), 120000L, "Transfer");
                Transaction transaction8 = new Transaction(accountService.getAccountByAccountId(1L), accountService.getAccountByAccountId(4L), 12000L, "Transfer");
                Transaction transaction9 = new Transaction(accountService.getAccountByAccountId(1L), accountService.getAccountByAccountId(4L), 20000L, "Transfer");

                transactionJpaRepository.saveAll(Arrays.asList(transaction1, transaction2, transaction3, transaction4, transaction5, transaction6, transaction7, transaction8, transaction9));
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
