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

    @Autowired
    private ReportedAccountJpaRepository reportedAccountJpaRepository;

    @Autowired
    private ReportsJpaRepository reportsJpaRepository;

    @Autowired
    private ReportAttachmentJpaRepository reportAttachmentJpaRepository;

    @Value("${seed.data.enabled:true}")
    private boolean seedDataEnabled;

    @Value("${seed.data.csv.path:data/accounts.csv}")
    private String csvFilePath;

    @Bean
    public CommandLineRunner accountSeederCommandLineRunner() {
        return args -> {
            if (seedDataEnabled && userJpaRepository.count() == 0) {
                User user1 = new User("Sofi Shahira Khoirun Nisa", "sofishr7", passwordHasherService.hashPassword("P11111"), passwordHasherService.hashPassword("111111"));
                User user2 = new User("Renata Rizki Rafi Athallah", "rizkirafi", passwordHasherService.hashPassword("P22222"), passwordHasherService.hashPassword("222222"));
                User user3 = new User("Jonathan Richard Sugandhi", "richiesugan", passwordHasherService.hashPassword("P33333"), passwordHasherService.hashPassword("333333"));
                User user4 = new User("Muhamad Dani Setiawan", "danti01", passwordHasherService.hashPassword("P44444"), passwordHasherService.hashPassword("444444"));
                User user5 = new User("Tiansi Ade Bora", "danti02", passwordHasherService.hashPassword("P55555"), passwordHasherService.hashPassword("555555"));
                User user6 = new User("Muhammad Rasidan Haikal", "haikal69", passwordHasherService.hashPassword("P66666"), passwordHasherService.hashPassword("666666"));
                User user7 = new User("Nabila Rizqa Damayanti", "nabir96", passwordHasherService.hashPassword("P77777"), passwordHasherService.hashPassword("777777"));
                User user8 = new User("Nurleila Rahmawati Sulistyo", "leilanrs", passwordHasherService.hashPassword("P88888"), passwordHasherService.hashPassword("888888"));
                User user9 = new User("Amelia Qatrunnada", "ameliaqat", passwordHasherService.hashPassword("P99999"), passwordHasherService.hashPassword("999999"));

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
                Transaction transaction3 = new Transaction(accountService.getAccountByAccountId(1L), accountService.getAccountByAccountId(8L), 20000L, "Transfer");
                Transaction transaction4 = new Transaction(accountService.getAccountByAccountId(2L), accountService.getAccountByAccountId(4L), 20000L, "Transfer");
                Transaction transaction5 = new Transaction(accountService.getAccountByAccountId(2L), accountService.getAccountByAccountId(4L), 20000L, "Transfer");
                Transaction transaction6 = new Transaction(accountService.getAccountByAccountId(3L), accountService.getAccountByAccountId(4L), 20000L, "Transfer");
                Transaction transaction7 = new Transaction(accountService.getAccountByAccountId(8L), accountService.getAccountByAccountId(10L), 20000L, "Transfer");
                Transaction transaction8 = new Transaction(accountService.getAccountByAccountId(1L), accountService.getAccountByAccountId(6L), 20000L, "Transfer");
                Transaction transaction9 = new Transaction(accountService.getAccountByAccountId(2L), accountService.getAccountByAccountId(4L), 20000L, "Transfer");

                transactionJpaRepository.saveAll(Arrays.asList(transaction1, transaction2, transaction3,transaction4,transaction5,transaction6,transaction7,transaction8,transaction9));
                if (seedDataEnabled && reportedAccountJpaRepository.count() == 0) {
                    ReportedAccount reportedAccount1= new ReportedAccount(transaction1.getAccountNumberDestination(), 1);
                    ReportedAccount reportedAccount2= new ReportedAccount(transaction3.getAccountNumberDestination(), 2);
                    ReportedAccount reportedAccount3= new ReportedAccount(transaction7.getAccountNumberDestination(), 3);
                    ReportedAccount reportedAccount4= new ReportedAccount(transaction8.getAccountNumberDestination(), 4);

                    reportedAccountJpaRepository.saveAll(Arrays.asList(reportedAccount1,reportedAccount2,reportedAccount3,reportedAccount4));
                }
            }
            if(seedDataEnabled && reportsJpaRepository.count() == 0){
                Transaction transaction1 = transactionJpaRepository.findTransactionByTransactionId(1L);
                Transaction transaction2 = transactionJpaRepository.findTransactionByTransactionId(2L);
                Transaction transaction3 = transactionJpaRepository.findTransactionByTransactionId(3L);
                Transaction transaction4 = transactionJpaRepository.findTransactionByTransactionId(4L);
                Transaction transaction5 = transactionJpaRepository.findTransactionByTransactionId(5L);
                Transaction transaction6 = transactionJpaRepository.findTransactionByTransactionId(6L);
                Transaction transaction7 = transactionJpaRepository.findTransactionByTransactionId(7L);
                Transaction transaction8 = transactionJpaRepository.findTransactionByTransactionId(8L);
                Transaction transaction9 = transactionJpaRepository.findTransactionByTransactionId(9L);

                Reports report1=new Reports("Sudah bayar barang tidak dikasih",transaction1, reportedAccountJpaRepository.findReportedAccountById(1));
                Reports report2=new Reports("Penipuan",transaction2, reportedAccountJpaRepository.findReportedAccountById(2));
                Reports report3=new Reports("Penipuan",transaction3, reportedAccountJpaRepository.findReportedAccountById(2));
                Reports report4=new Reports("Penipuan",transaction4, reportedAccountJpaRepository.findReportedAccountById(3));
                Reports report5=new Reports("Penipuan",transaction5, reportedAccountJpaRepository.findReportedAccountById(3));
                Reports report6=new Reports("Penipuan",transaction7, reportedAccountJpaRepository.findReportedAccountById(1));
                Reports report7=new Reports("Pembohong",transaction8, reportedAccountJpaRepository.findReportedAccountById(2));
                Reports report8=new Reports("Pembohong",transaction9, reportedAccountJpaRepository.findReportedAccountById(3));

                reportsJpaRepository.saveAll(Arrays.asList(report1,report2,report3,report4,report5,report6,report7,report8));
            }
            if(seedDataEnabled && reportAttachmentJpaRepository.count() ==0){
                Reports report1=reportsJpaRepository.findReportsByReportId(1L);
                Reports report2=reportsJpaRepository.findReportsByReportId(2L);
                Reports report3=reportsJpaRepository.findReportsByReportId(3L);
                Reports report4=reportsJpaRepository.findReportsByReportId(4L);
                Reports report5=reportsJpaRepository.findReportsByReportId(5L);
                Reports report6=reportsJpaRepository.findReportsByReportId(6L);
                Reports report7=reportsJpaRepository.findReportsByReportId(7L);
                Reports report8=reportsJpaRepository.findReportsByReportId(8L);

                ReportAttachment reportAttachment1= new ReportAttachment(report1, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment2= new ReportAttachment(report2, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment3= new ReportAttachment(report3, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment4= new ReportAttachment(report4, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment5= new ReportAttachment(report5, "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/01/2023/07/19/penipuan-bank-3632286447.jpg");
                ReportAttachment reportAttachment6= new ReportAttachment(report6, "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/01/2023/07/19/penipuan-bank-3632286447.jpg");
                ReportAttachment reportAttachment7= new ReportAttachment(report7, "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/01/2023/07/19/penipuan-bank-3632286447.jpg");
                ReportAttachment reportAttachment8= new ReportAttachment(report8, "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/01/2023/07/19/penipuan-bank-3632286447.jpg");
                reportAttachmentJpaRepository.saveAll(Arrays.asList(reportAttachment1,reportAttachment2,reportAttachment3,reportAttachment4,reportAttachment5,reportAttachment6,reportAttachment7,reportAttachment8));
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