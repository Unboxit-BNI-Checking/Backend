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

    @Autowired
    private FavouriteJpaRepository favouriteJpaRepository;

    @Value("${seed.data.enabled:true}")
    private boolean seedDataEnabled;

    @Value("${seed.data.csv.path:data/accounts.csv}")
    private String csvFilePath;

    @Bean
    public CommandLineRunner accountSeederCommandLineRunner() {
        return args -> {
            if (seedDataEnabled && userJpaRepository.count() == 0) {
                User user1 = new User("Poppy", "poppy", passwordHasherService.hashPassword("P11111"), passwordHasherService.hashPassword("111111"));
                User user2 = new User("Renata Rizki Rafi Athallah", "rizkirafi", passwordHasherService.hashPassword("P22222"), passwordHasherService.hashPassword("222222"));
                User user3 = new User("Jonathan Richard Sugandhi", "richiesugan", passwordHasherService.hashPassword("P33333"), passwordHasherService.hashPassword("333333"));
                User user4 = new User("Muhamad Dani Setiawan", "danti01", passwordHasherService.hashPassword("P44444"), passwordHasherService.hashPassword("444444"));
                User user5 = new User("Tiansi Ade Bora", "danti02", passwordHasherService.hashPassword("P55555"), passwordHasherService.hashPassword("555555"));
                User user6 = new User("Muhammad Rasidan Haikal", "haikal69", passwordHasherService.hashPassword("P66666"), passwordHasherService.hashPassword("666666"));
                User user7 = new User("Nabila Rizqa Damayanti", "nabir96", passwordHasherService.hashPassword("P77777"), passwordHasherService.hashPassword("777777"));
                User user8 = new User("Nurleila Rahmawati Sulistyo", "leilanrs", passwordHasherService.hashPassword("P88888"), passwordHasherService.hashPassword("888888"));
                User user9 = new User("Amelia Qatrunnada", "ameliaqat", passwordHasherService.hashPassword("P99999"), passwordHasherService.hashPassword("999999"));
                User user10 = new User("Rivendrea", "riven", passwordHasherService.hashPassword("P10101"), passwordHasherService.hashPassword("101010"));
                User user11 = new User("Gilang", "gilang", passwordHasherService.hashPassword("P11011"), passwordHasherService.hashPassword("110011"));
                User user12 = new User("Alfredo", "dodo", passwordHasherService.hashPassword("P12121"), passwordHasherService.hashPassword("121212"));
                User user13 = new User("Raffly", "raffly", passwordHasherService.hashPassword("P13131"), passwordHasherService.hashPassword("131313"));
                User user14 = new User("Rayhan", "rayhan", passwordHasherService.hashPassword("P14141"), passwordHasherService.hashPassword("141414"));
                User user15 = new User("David", "david", passwordHasherService.hashPassword("P15151"), passwordHasherService.hashPassword("151515"));
                User user16 = new User("Aldy", "aldy", passwordHasherService.hashPassword("P16161"), passwordHasherService.hashPassword("161616"));
                User user17 = new User("Noriq", "noriq", passwordHasherService.hashPassword("P17171"), passwordHasherService.hashPassword("171717"));
                User user18 = new User("Rima", "rima", passwordHasherService.hashPassword("P18181"), passwordHasherService.hashPassword("181818"));
                User user19 = new User("Akhdan", "akhdan", passwordHasherService.hashPassword("P19191"), passwordHasherService.hashPassword("191919"));

                //user demo
                User user20 = new User("Sofi Shahira Khoirun Nisa", "sofishr7", passwordHasherService.hashPassword("P12345"), passwordHasherService.hashPassword("123456"));

                userJpaRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12, user13, user14, user15, user16, user17, user18, user19, user20));

                if (seedDataEnabled && accountJpaRepository.count() == 0) {
                    Account account1 = new Account("1813111111", user1, 10000000L, false);
                    Account account2 = new Account("1813111112", user1, 1233000L, false);
                    Account account3 = new Account("1813111113", user1, 120000L, false);
                    Account account4 = new Account("1813222221", user2, 11000000L, false);
                    Account account5 = new Account("1813222222", user2, 2542213L, false);
                    Account account6 = new Account("1813222223", user2, 143700L, false);
                    Account account7 = new Account("1813333331", user3, 12000000L, false);
                    Account account8 = new Account("1813333332", user3, 1212300L, false);
                    Account account9 = new Account("1813333333", user3, 143300L,    false);
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
                    Account account28 = new Account("1794074334", user10, 1000000L, false);
                    Account account29 = new Account("1734637142", user11, 6362712L, false);
                    Account account30 = new Account("1793878382", user12, 890741L, false);
                    Account account31 = new Account("1800389180", user13, 1000000L, false);
                    Account account32 = new Account("1608388495", user14, 3748102L, false);
                    Account account33 = new Account("1797932074", user15, 567431L, false);
                    Account account34 = new Account("1825051698", user16, 1000000L, false);
                    Account account35 = new Account("1685998012", user17, 6374182L, false);
                    Account account36 = new Account("1812483828", user18, 236700L, false);
                    Account account37 = new Account("1701016265", user19, 236700L, false);

                    //Account demo
                    Account account38 = new Account("1813123456", user20, 10000000L, false);

                    accountJpaRepository.saveAll(Arrays.asList(account1, account2, account3, account4, account5, account6, account7, account8, account9, account10, account11, account12, account13, account14, account15, account16, account17, account18, account19, account20, account21, account22, account23, account24, account25, account26, account27, account28, account29, account30, account31, account32, account33, account34, account35, account36, account37, account38));

                    //favourite demo
                    if (seedDataEnabled && favouriteJpaRepository.count() == 0) {
                        Favourite favourite1 = new Favourite(account5, "BNI - Rizki", user20);
                        Favourite favourite2 = new Favourite(account7, "BNI - Richie", user20);
                        favouriteJpaRepository.saveAll(Arrays.asList(favourite1, favourite2));
                    }
                }
            }

            if (seedDataEnabled && adminJpaRepository.count() == 0) {
                Admins admin1 = new Admins("admin1", passwordHasherService.hashPassword("password1"));
                Admins admin2 = new Admins("admin2", passwordHasherService.hashPassword("password2"));
                Admins admin3 = new Admins("admin3", passwordHasherService.hashPassword("password3"));
                Admins admin4 = new Admins("admin4", passwordHasherService.hashPassword("password4"));

                adminJpaRepository.saveAll(Arrays.asList(admin1, admin2, admin3, admin4));
            }

            if (seedDataEnabled && transactionJpaRepository.count() == 0) {
                Transaction transaction1 = new Transaction(accountService.getAccountByAccountId(1L), accountService.getAccountByAccountId(27L), 900000L, "Transfer");
                Transaction transaction2 = new Transaction(accountService.getAccountByAccountId(2L), accountService.getAccountByAccountId(22L), 21313L, "Transfer");
                Transaction transaction3 = new Transaction(accountService.getAccountByAccountId(2L), accountService.getAccountByAccountId(14L), 12900L, "Transfer");
                Transaction transaction4 = new Transaction(accountService.getAccountByAccountId(1L), accountService.getAccountByAccountId(5L), 121000L, "Transfer");
                Transaction transaction5 = new Transaction(accountService.getAccountByAccountId(3L), accountService.getAccountByAccountId(7L), 132100L, "Transfer");
                Transaction transaction6 = new Transaction(accountService.getAccountByAccountId(28L), accountService.getAccountByAccountId(1L), 213100L, "Transfer");
                Transaction transaction7 = new Transaction(accountService.getAccountByAccountId(29L), accountService.getAccountByAccountId(27L), 89000L, "Transfer");
                Transaction transaction8 = new Transaction(accountService.getAccountByAccountId(30L), accountService.getAccountByAccountId(22L), 78000L, "Transfer");
                Transaction transaction9 = new Transaction(accountService.getAccountByAccountId(31L), accountService.getAccountByAccountId(5L), 150000L, "Transfer");
                Transaction transaction10 = new Transaction(accountService.getAccountByAccountId(32L), accountService.getAccountByAccountId(7L), 8000L, "Transfer");
                Transaction transaction11 = new Transaction(accountService.getAccountByAccountId(33L), accountService.getAccountByAccountId(4L), 21000L, "Transfer");
                Transaction transaction12 = new Transaction(accountService.getAccountByAccountId(33L), accountService.getAccountByAccountId(10L), 56000L, "Transfer");
                Transaction transaction13 = new Transaction(accountService.getAccountByAccountId(34L), accountService.getAccountByAccountId(4L), 10000L, "Transfer");
                Transaction transaction14 = new Transaction(accountService.getAccountByAccountId(34L), accountService.getAccountByAccountId(6L), 11000L, "Transfer");
                Transaction transaction15 = new Transaction(accountService.getAccountByAccountId(35L), accountService.getAccountByAccountId(1L), 689000L, "Transfer");
                Transaction transaction16 = new Transaction(accountService.getAccountByAccountId(35L), accountService.getAccountByAccountId(5L), 900000L, "Transfer");
                Transaction transaction17 = new Transaction(accountService.getAccountByAccountId(36L), accountService.getAccountByAccountId(4L), 21313L, "Transfer");
                Transaction transaction18 = new Transaction(accountService.getAccountByAccountId(36L), accountService.getAccountByAccountId(7L), 12900L, "Transfer");
                Transaction transaction19 = new Transaction(accountService.getAccountByAccountId(37L), accountService.getAccountByAccountId(19L), 121000L, "Transfer");
                Transaction transaction20 = new Transaction(accountService.getAccountByAccountId(37L), accountService.getAccountByAccountId(6L), 132100L, "Transfer");
                Transaction transaction21 = new Transaction(accountService.getAccountByAccountId(4L), accountService.getAccountByAccountId(19L), 213100L, "Transfer");
                Transaction transaction22 = new Transaction(accountService.getAccountByAccountId(4L), accountService.getAccountByAccountId(20L), 89000L, "Transfer");
                Transaction transaction23 = new Transaction(accountService.getAccountByAccountId(7L), accountService.getAccountByAccountId(19L), 78000L, "Transfer");
                Transaction transaction24 = new Transaction(accountService.getAccountByAccountId(7L), accountService.getAccountByAccountId(21L), 150000L, "Transfer");
                Transaction transaction25 = new Transaction(accountService.getAccountByAccountId(10L), accountService.getAccountByAccountId(1L), 8000L, "Transfer");
                Transaction transaction26 = new Transaction(accountService.getAccountByAccountId(11L), accountService.getAccountByAccountId(19L), 21000L, "Transfer");
                Transaction transaction27 = new Transaction(accountService.getAccountByAccountId(12L), accountService.getAccountByAccountId(28L), 56000L, "Transfer");
                Transaction transaction28 = new Transaction(accountService.getAccountByAccountId(13L), accountService.getAccountByAccountId(4L), 10000L, "Transfer");
                Transaction transaction29 = new Transaction(accountService.getAccountByAccountId(14L), accountService.getAccountByAccountId(10L), 11000L, "Transfer");
                Transaction transaction30 = new Transaction(accountService.getAccountByAccountId(15L), accountService.getAccountByAccountId(5L), 689000L, "Transfer");
                Transaction transaction31 = new Transaction(accountService.getAccountByAccountId(16L), accountService.getAccountByAccountId(1L), 900000L, "Transfer");
                Transaction transaction32 = new Transaction(accountService.getAccountByAccountId(17L), accountService.getAccountByAccountId(10L), 21313L, "Transfer");
                Transaction transaction33 = new Transaction(accountService.getAccountByAccountId(18L), accountService.getAccountByAccountId(21L), 12900L, "Transfer");
                Transaction transaction34 = new Transaction(accountService.getAccountByAccountId(19L), accountService.getAccountByAccountId(28L), 121000L, "Transfer");
                Transaction transaction35 = new Transaction(accountService.getAccountByAccountId(20L), accountService.getAccountByAccountId(29L), 132100L, "Transfer");
                Transaction transaction36 = new Transaction(accountService.getAccountByAccountId(21L), accountService.getAccountByAccountId(30L), 213100L, "Transfer");
                Transaction transaction37 = new Transaction(accountService.getAccountByAccountId(22L), accountService.getAccountByAccountId(10L), 89000L, "Transfer");
                Transaction transaction38 = new Transaction(accountService.getAccountByAccountId(24L), accountService.getAccountByAccountId(28L), 78000L, "Transfer");
                Transaction transaction39 = new Transaction(accountService.getAccountByAccountId(23L), accountService.getAccountByAccountId(29L), 150000L, "Transfer");
                Transaction transaction40 = new Transaction(accountService.getAccountByAccountId(22L), accountService.getAccountByAccountId(30L), 8000L, "Transfer");
                Transaction transaction41 = new Transaction(accountService.getAccountByAccountId(25L), accountService.getAccountByAccountId(1L), 21000L, "Transfer");
                Transaction transaction42 = new Transaction(accountService.getAccountByAccountId(26L), accountService.getAccountByAccountId(28L), 56000L, "Transfer");
                Transaction transaction43 = new Transaction(accountService.getAccountByAccountId(25L), accountService.getAccountByAccountId(29L), 10000L, "Transfer");
                Transaction transaction44 = new Transaction(accountService.getAccountByAccountId(27L), accountService.getAccountByAccountId(30L), 11000L, "Transfer");

                //Transacstion demo
                Transaction transaction45 = new Transaction(accountService.getAccountByAccountId(38L), accountService.getAccountByAccountId(25L), 900000L, "Transfer");
                Transaction transaction46 = new Transaction(accountService.getAccountByAccountId(38L), accountService.getAccountByAccountId(26L), 21313L, "Transfer");
                Transaction transaction47 = new Transaction(accountService.getAccountByAccountId(38L), accountService.getAccountByAccountId(25L), 12900L, "Transfer");
                Transaction transaction48 = new Transaction(accountService.getAccountByAccountId(38L), accountService.getAccountByAccountId(24L), 121000L, "Transfer");
                Transaction transaction49 = new Transaction(accountService.getAccountByAccountId(38L), accountService.getAccountByAccountId(23L), 132100L, "Transfer");
                Transaction transaction50 = new Transaction(accountService.getAccountByAccountId(38L), accountService.getAccountByAccountId(23L), 213100L, "Transfer");
                Transaction transaction51 = new Transaction(accountService.getAccountByAccountId(38L), accountService.getAccountByAccountId(24L), 89000L, "Transfer");
                Transaction transaction52 = new Transaction(accountService.getAccountByAccountId(38L), accountService.getAccountByAccountId(23L), 78000L, "Transfer");
                Transaction transaction53 = new Transaction(accountService.getAccountByAccountId(38L), accountService.getAccountByAccountId(25L), 150000L, "Transfer");
                Transaction transaction54 = new Transaction(accountService.getAccountByAccountId(38L), accountService.getAccountByAccountId(26L), 8000L, "Transfer");

                Transaction transaction55 = new Transaction(accountService.getAccountByAccountId(37L), accountService.getAccountByAccountId(19L), 100000L, "Transfer");

                Transaction transaction56 = new Transaction(accountService.getAccountByAccountId(37L), accountService.getAccountByAccountId(6L), 100000L, "Transfer");
                Transaction transaction57 = new Transaction(accountService.getAccountByAccountId(37L), accountService.getAccountByAccountId(6L), 100000L, "Transfer");
                Transaction transaction58 = new Transaction(accountService.getAccountByAccountId(37L), accountService.getAccountByAccountId(6L), 100000L, "Transfer");
                Transaction transaction59 = new Transaction(accountService.getAccountByAccountId(37L), accountService.getAccountByAccountId(6L), 100000L, "Transfer");
                Transaction transaction60 = new Transaction(accountService.getAccountByAccountId(37L), accountService.getAccountByAccountId(6L), 100000L, "Transfer");
                Transaction transaction61 = new Transaction(accountService.getAccountByAccountId(37L), accountService.getAccountByAccountId(6L), 100000L, "Transfer");
                Transaction transaction62 = new Transaction(accountService.getAccountByAccountId(37L), accountService.getAccountByAccountId(6L), 100000L, "Transfer");
                Transaction transaction63 = new Transaction(accountService.getAccountByAccountId(37L), accountService.getAccountByAccountId(6L), 100000L, "Transfer");


                transactionJpaRepository.saveAll(Arrays.asList(
                        transaction1, transaction2, transaction3,transaction4,transaction5,
                        transaction6,transaction7,transaction8,transaction9, transaction10,
                        transaction11, transaction12, transaction13,transaction14,transaction15,
                        transaction16, transaction17, transaction18,transaction19,transaction20,
                        transaction21,transaction22,transaction23,transaction24, transaction25,
                        transaction26, transaction27, transaction28,transaction29,transaction30,
                        transaction31, transaction32, transaction33,transaction34,transaction35,
                        transaction36,transaction37,transaction38,transaction39, transaction40,
                        transaction41, transaction42, transaction43,transaction44, transaction45, transaction46, transaction47,transaction48,transaction49,
                        transaction50,transaction51,transaction52,transaction53, transaction54,
                        transaction55, transaction56,transaction57,transaction58,transaction59, transaction60,
                        transaction61, transaction62, transaction63));

                if (seedDataEnabled && reportedAccountJpaRepository.count() == 0) {
                    Admins admin1 =  adminJpaRepository.findAdminsByAdminId(1L);
                    Admins admin2 =  adminJpaRepository.findAdminsByAdminId(2L);
                    Admins admin3 =  adminJpaRepository.findAdminsByAdminId(3L);
                    Admins admin4 =  adminJpaRepository.findAdminsByAdminId(4L);

                    ReportedAccount reportedAccount1= new ReportedAccount(transaction1.getAccountNumberDestination(), 2, admin1);
                    ReportedAccount reportedAccount2= new ReportedAccount(transaction2.getAccountNumberDestination(), 3, admin1 ,LocalDateTime.now());
                    ReportedAccount reportedAccount3= new ReportedAccount(transaction3.getAccountNumberDestination(), 1);
                    ReportedAccount reportedAccount4= new ReportedAccount(transaction4.getAccountNumberDestination(), 4, admin2, LocalDateTime.now());
                    ReportedAccount reportedAccount5= new ReportedAccount(transaction5.getAccountNumberDestination(), 5, admin3,LocalDateTime.now());
                    ReportedAccount reportedAccount6= new ReportedAccount(transaction6.getAccountNumberDestination(), 1);
                    ReportedAccount reportedAccount7= new ReportedAccount(transaction11.getAccountNumberDestination(), 1);
                    ReportedAccount reportedAccount8= new ReportedAccount(transaction12.getAccountNumberDestination(), 2, admin1);
                    ReportedAccount reportedAccount9= new ReportedAccount(transaction14.getAccountNumberDestination(), 3, admin2,LocalDateTime.now());
                    ReportedAccount reportedAccount10= new ReportedAccount(transaction19.getAccountNumberDestination(), 2, admin1);
                    ReportedAccount reportedAccount11= new ReportedAccount(transaction22.getAccountNumberDestination(), 4, admin4,LocalDateTime.now());
                    ReportedAccount reportedAccount12= new ReportedAccount(transaction24.getAccountNumberDestination(), 5, admin3,LocalDateTime.now());
                    ReportedAccount reportedAccount13= new ReportedAccount(transaction27.getAccountNumberDestination(), 3, admin2,LocalDateTime.now());
                    ReportedAccount reportedAccount14= new ReportedAccount(transaction35.getAccountNumberDestination(), 4, admin1,LocalDateTime.now());
                    ReportedAccount reportedAccount15= new ReportedAccount(transaction36.getAccountNumberDestination(), 5, admin2,LocalDateTime.now());

                    reportedAccountJpaRepository.saveAll(Arrays.asList(
                            reportedAccount1,reportedAccount2,reportedAccount3,reportedAccount4, reportedAccount5,
                            reportedAccount6,reportedAccount7,reportedAccount8,reportedAccount9, reportedAccount10,
                            reportedAccount11,reportedAccount12,reportedAccount13,reportedAccount14, reportedAccount15));
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
                Transaction transaction10 = transactionJpaRepository.findTransactionByTransactionId(10L);
                Transaction transaction11 = transactionJpaRepository.findTransactionByTransactionId(11L);
                Transaction transaction12 = transactionJpaRepository.findTransactionByTransactionId(12L);
                Transaction transaction13 = transactionJpaRepository.findTransactionByTransactionId(13L);
                Transaction transaction14 = transactionJpaRepository.findTransactionByTransactionId(14L);
                Transaction transaction15 = transactionJpaRepository.findTransactionByTransactionId(15L);
                Transaction transaction16 = transactionJpaRepository.findTransactionByTransactionId(16L);
                Transaction transaction17 = transactionJpaRepository.findTransactionByTransactionId(17L);
                Transaction transaction18 = transactionJpaRepository.findTransactionByTransactionId(18L);
                Transaction transaction19 = transactionJpaRepository.findTransactionByTransactionId(19L);
                Transaction transaction20 = transactionJpaRepository.findTransactionByTransactionId(20L);
                Transaction transaction21 = transactionJpaRepository.findTransactionByTransactionId(21L);
                Transaction transaction22 = transactionJpaRepository.findTransactionByTransactionId(22L);
                Transaction transaction23 = transactionJpaRepository.findTransactionByTransactionId(23L);
                Transaction transaction24 = transactionJpaRepository.findTransactionByTransactionId(24L);
                Transaction transaction25 = transactionJpaRepository.findTransactionByTransactionId(25L);
                Transaction transaction26 = transactionJpaRepository.findTransactionByTransactionId(26L);
                Transaction transaction27 = transactionJpaRepository.findTransactionByTransactionId(27L);
                Transaction transaction28 = transactionJpaRepository.findTransactionByTransactionId(28L);
                Transaction transaction29 = transactionJpaRepository.findTransactionByTransactionId(29L);
                Transaction transaction30 = transactionJpaRepository.findTransactionByTransactionId(30L);
                Transaction transaction31 = transactionJpaRepository.findTransactionByTransactionId(31L);
                Transaction transaction32 = transactionJpaRepository.findTransactionByTransactionId(32L);
                Transaction transaction33 = transactionJpaRepository.findTransactionByTransactionId(33L);
                Transaction transaction34 = transactionJpaRepository.findTransactionByTransactionId(34L);
                Transaction transaction35 = transactionJpaRepository.findTransactionByTransactionId(35L);
                Transaction transaction36 = transactionJpaRepository.findTransactionByTransactionId(36L);
                Transaction transaction37 = transactionJpaRepository.findTransactionByTransactionId(37L);
                Transaction transaction38 = transactionJpaRepository.findTransactionByTransactionId(38L);
                Transaction transaction39 = transactionJpaRepository.findTransactionByTransactionId(39L);
                Transaction transaction40 = transactionJpaRepository.findTransactionByTransactionId(40L);
                Transaction transaction41 = transactionJpaRepository.findTransactionByTransactionId(41L);
                Transaction transaction42 = transactionJpaRepository.findTransactionByTransactionId(42L);
                Transaction transaction43 = transactionJpaRepository.findTransactionByTransactionId(43L);
                Transaction transaction44 = transactionJpaRepository.findTransactionByTransactionId(44L);
                Transaction transaction55 = transactionJpaRepository.findTransactionByTransactionId(55L);
                Transaction transaction56 = transactionJpaRepository.findTransactionByTransactionId(56L);
                Transaction transaction57 = transactionJpaRepository.findTransactionByTransactionId(57L);
                Transaction transaction58 = transactionJpaRepository.findTransactionByTransactionId(58L);
                Transaction transaction59 = transactionJpaRepository.findTransactionByTransactionId(59L);
                Transaction transaction60 = transactionJpaRepository.findTransactionByTransactionId(60L);
                Transaction transaction61 = transactionJpaRepository.findTransactionByTransactionId(61L);
                Transaction transaction62 = transactionJpaRepository.findTransactionByTransactionId(62L);
                Transaction transaction63 = transactionJpaRepository.findTransactionByTransactionId(63L);

                Reports report1=new Reports("Saya menerima panggilan telepon palsu yang mengklaim berasal dari Bank BNI dan diminta untuk transfer",transaction1, reportedAccountJpaRepository.findReportedAccountById(1));
                Reports report2=new Reports("Penipuan hadiah",transaction2, reportedAccountJpaRepository.findReportedAccountById(2));
                Reports report3=new Reports("elaku menawarkan investasi palsu dengan imbal hasil yang tinggi kepada korban. Setelah menerima uang dari korban, pelaku menghilang tanpa memberikan hasil investasi yang dijanjikan.",transaction3, reportedAccountJpaRepository.findReportedAccountById(3));
                Reports report4=new Reports("Saya membeli barang akan tetapi barang tidak dikirim",transaction4, reportedAccountJpaRepository.findReportedAccountById(4));
                Reports report5=new Reports("Pelaku menawarkan pekerjaan palsu kepada korban dengan janji gaji yang tinggi. Setelah korban menerima \"gaji pertama\", mereka diminta untuk mengirim kembali sebagian uangnya sebagai \"biaya administrasi\", tetapi transfer tersebut sebenarnya ke rekening pelaku.",transaction5, reportedAccountJpaRepository.findReportedAccountById(5));
                Reports report6=new Reports("Penipuan hadiah",transaction6, reportedAccountJpaRepository.findReportedAccountById(6));
                Reports report7=new Reports("Pelaku memalsukan penjualan online, menerima pembayaran dari korban, tetapi tidak memberikan produk atau layanan yang dijanjikan.",transaction7, reportedAccountJpaRepository.findReportedAccountById(1));
                Reports report8=new Reports("Saya membeli barang akan tetapi barang tidak dikirim",transaction8, reportedAccountJpaRepository.findReportedAccountById(2));
                Reports report9=new Reports("Pelaku menawarkan investasi palsu dalam kriptokurensi atau skema ponzi yang menghasilkan imbal hasil yang tidak realistis, dan kemudian menghilang dengan dana yang diinvestasikan.",transaction9, reportedAccountJpaRepository.findReportedAccountById(4));
                Reports report10=new Reports("Pelaku menyamar sebagai petugas pelayanan darurat (misalnya polisi, dokter, atau pengacara) dan meminta pembayaran segera untuk biaya pengobatan atau hukum yang tidak ada.",transaction10, reportedAccountJpaRepository.findReportedAccountById(5));
                Reports report11=new Reports("Pelaku menawarkan pekerjaan palsu kepada korban dengan janji gaji yang tinggi. Mereka kemudian meminta korban untuk membayar biaya pendaftaran atau biaya lainnya, yang pada akhirnya digunakan oleh pelaku untuk melakukan transfer uang.",transaction11, reportedAccountJpaRepository.findReportedAccountById(7));
                Reports report12=new Reports("Pelaku menawarkan investasi palsu dalam kriptokurensi atau skema ponzi yang menghasilkan imbal hasil yang tidak realistis, dan kemudian menghilang dengan dana yang diinvestasikan.",transaction12, reportedAccountJpaRepository.findReportedAccountById(8));
                Reports report13=new Reports("Pelaku menawarkan pekerjaan palsu kepada korban dengan janji gaji yang tinggi. Setelah korban menerima \"gaji pertama\", mereka diminta untuk mengirim kembali sebagian uangnya sebagai \"biaya administrasi\", tetapi transfer tersebut sebenarnya ke rekening pelaku.",transaction13, reportedAccountJpaRepository.findReportedAccountById(7));
                Reports report14=new Reports("Pelaku membangun hubungan romantis dengan korban secara online, lalu meminta uang untuk \"keadaan darurat\" atau biaya perjalanan yang palsu.",transaction14, reportedAccountJpaRepository.findReportedAccountById(9));
                Reports report15=new Reports("Pelaku meretas sistem informasi pelabuhan dan mengarahkan transfer uang yang seharusnya untuk pembayaran barang impor ke rekening mereka sendiri.",transaction15, reportedAccountJpaRepository.findReportedAccountById(6));
                Reports report16=new Reports("Pelaku memasarkan tiket acara palsu dan menerima pembayaran, tetapi tidak memberikan tiket atau akses ke acara yang dijanjikan.",transaction16, reportedAccountJpaRepository.findReportedAccountById(4));
                Reports report17=new Reports("Pelaku membuat organisasi amal palsu atau mengklaim bahwa mereka mengumpulkan dana untuk tujuan amal, tetapi sebenarnya uang yang dikumpulkan digunakan untuk keuntungan pribadi mereka.\n",transaction17, reportedAccountJpaRepository.findReportedAccountById(7));
                Reports report18=new Reports("Pelaku menggunakan identitas palsu dari lembaga pemerintah untuk meyakinkan korban bahwa mereka memiliki klaim atau pembayaran tertunda yang harus dikirim ke rekening mereka.",transaction18, reportedAccountJpaRepository.findReportedAccountById(5));
                Reports report19=new Reports("Pelaku menawarkan polis asuransi palsu atau mengklaim klaim palsu untuk mendapatkan pembayaran asuransi.",transaction19, reportedAccountJpaRepository.findReportedAccountById(10));
                Reports report20=new Reports("Pelaku menjual barang palsu atau palsu secara online, menerima pembayaran dari korban, tetapi tidak memberikan barang yang dijanjikan.",transaction20, reportedAccountJpaRepository.findReportedAccountById(9));
                Reports report21=new Reports("Pelaku menyamar sebagai agen pajak atau lembaga pajak palsu dan meminta pembayaran pajak palsu atau biaya lainnya yang seharusnya tidak ada",transaction21, reportedAccountJpaRepository.findReportedAccountById(10));
                Reports report22=new Reports("Pelaku menawarkan \"beasiswa\" palsu atau bantuan pendidikan dan meminta pembayaran sebagai biaya administrasi atau pendaftaran, tetapi tidak ada bantuan pendidikan yang diberikan kepada korban.",transaction22, reportedAccountJpaRepository.findReportedAccountById(11));
                Reports report23=new Reports("Pelaku menjual properti palsu atau tanah yang tidak mereka miliki, menerima pembayaran dari korban, tetapi tidak memberikan kepemilikan yang sah atas properti tersebut.",transaction23, reportedAccountJpaRepository.findReportedAccountById(10));
                Reports report24=new Reports("Pelaku menjual kendaraan palsu secara online dan menerima pembayaran dari korban, tetapi tidak memberikan kendaraan yang dijanjikan.",transaction24, reportedAccountJpaRepository.findReportedAccountById(12));
                Reports report25=new Reports("Pelaku mengklaim bahwa korban telah memenangkan hadiah besar dan meminta pembayaran kecil sebagai biaya pengiriman atau administrasi, tetapi tidak ada hadiah yang diterima oleh korban.",transaction25, reportedAccountJpaRepository.findReportedAccountById(6));
                Reports report26=new Reports("Pelaku menawarkan produk atau layanan kesehatan palsu dengan janji kesembuhan atau perbaikan kesehatan yang tidak mungkin, dan menerima pembayaran dari korban tanpa memberikan manfaat yang dijanjikan.",transaction26, reportedAccountJpaRepository.findReportedAccountById(10));
                Reports report27=new Reports("Pelaku menawarkan \"beasiswa\" palsu atau bantuan pendidikan dan meminta pembayaran sebagai biaya administrasi atau pendaftaran, tetapi tidak ada bantuan pendidikan yang diberikan kepada korban.",transaction27, reportedAccountJpaRepository.findReportedAccountById(13));
                Reports report28=new Reports("Pelaku menyatakan bahwa mereka menemukan dompet yang hilang dan mengklaim bahwa mereka akan mengembalikan uang yang ada di dalamnya setelah korban mentransfer sejumlah uang kepada mereka.",transaction28, reportedAccountJpaRepository.findReportedAccountById(7));
                Reports report29=new Reports("Pelaku mengklaim bahwa korban telah memenangkan hadiah besar dan meminta pembayaran kecil sebagai biaya pengiriman atau administrasi, tetapi tidak ada hadiah yang diterima oleh korban.",transaction29, reportedAccountJpaRepository.findReportedAccountById(8));
                Reports report30=new Reports("Pelaku menyatakan bahwa mereka menemukan dompet yang hilang dan mengklaim bahwa mereka akan mengembalikan uang yang ada di dalamnya setelah korban mentransfer sejumlah uang kepada mereka.",transaction30, reportedAccountJpaRepository.findReportedAccountById(4));
                Reports report31=new Reports("Pelaku menawarkan \"beasiswa\" palsu atau bantuan pendidikan dan meminta pembayaran sebagai biaya administrasi atau pendaftaran, tetapi tidak ada bantuan pendidikan yang diberikan kepada korban.",transaction31, reportedAccountJpaRepository.findReportedAccountById(6));
                Reports report32=new Reports("Pelaku menyatakan bahwa mereka menemukan dompet yang hilang dan mengklaim bahwa mereka akan mengembalikan uang yang ada di dalamnya setelah korban mentransfer sejumlah uang kepada mereka.",transaction32, reportedAccountJpaRepository.findReportedAccountById(8));
                Reports report33=new Reports("Pelaku menjual kendaraan palsu secara online dan menerima pembayaran dari korban, tetapi tidak memberikan kendaraan yang dijanjikan.",transaction33, reportedAccountJpaRepository.findReportedAccountById(12));
                Reports report34=new Reports("Pelaku menyatakan bahwa mereka menemukan dompet yang hilang dan mengklaim bahwa mereka akan mengembalikan uang yang ada di dalamnya setelah korban mentransfer sejumlah uang kepada mereka.",transaction34, reportedAccountJpaRepository.findReportedAccountById(13));
                Reports report35=new Reports("Pelaku menawarkan \"beasiswa\" palsu atau bantuan pendidikan dan meminta pembayaran sebagai biaya administrasi atau pendaftaran, tetapi tidak ada bantuan pendidikan yang diberikan kepada korban.",transaction35, reportedAccountJpaRepository.findReportedAccountById(14));
                Reports report36=new Reports("Pelaku mengklaim bahwa korban telah memenangkan hadiah besar dan meminta pembayaran kecil sebagai biaya pengiriman atau administrasi, tetapi tidak ada hadiah yang diterima oleh korban.",transaction36, reportedAccountJpaRepository.findReportedAccountById(15));
                Reports report37=new Reports("Pelaku menyatakan bahwa mereka menemukan dompet yang hilang dan mengklaim bahwa mereka akan mengembalikan uang yang ada di dalamnya setelah korban mentransfer sejumlah uang kepada mereka.",transaction37, reportedAccountJpaRepository.findReportedAccountById(8));
                Reports report38=new Reports("Pelaku menawarkan \"beasiswa\" palsu atau bantuan pendidikan dan meminta pembayaran sebagai biaya administrasi atau pendaftaran, tetapi tidak ada bantuan pendidikan yang diberikan kepada korban.",transaction38, reportedAccountJpaRepository.findReportedAccountById(13));
                Reports report39=new Reports("Pelaku mengklaim bahwa korban telah memenangkan hadiah besar dan meminta pembayaran kecil sebagai biaya pengiriman atau administrasi, tetapi tidak ada hadiah yang diterima oleh korban.",transaction39, reportedAccountJpaRepository.findReportedAccountById(14));
                Reports report40=new Reports("Pelaku menawarkan produk atau layanan kesehatan palsu dengan janji kesembuhan atau perbaikan kesehatan yang tidak mungkin, dan menerima pembayaran dari korban tanpa memberikan manfaat yang dijanjikan.",transaction40, reportedAccountJpaRepository.findReportedAccountById(15));
                Reports report41=new Reports("Pelaku menawarkan produk atau layanan kesehatan palsu dengan janji kesembuhan atau perbaikan kesehatan yang tidak mungkin, dan menerima pembayaran dari korban tanpa memberikan manfaat yang dijanjikan.",transaction41, reportedAccountJpaRepository.findReportedAccountById(6));
                Reports report42=new Reports("Pelaku menyatakan bahwa mereka menemukan dompet yang hilang dan mengklaim bahwa mereka akan mengembalikan uang yang ada di dalamnya setelah korban mentransfer sejumlah uang kepada mereka.",transaction42, reportedAccountJpaRepository.findReportedAccountById(13));
                Reports report43=new Reports("Pelaku menawarkan \"beasiswa\" palsu atau bantuan pendidikan dan meminta pembayaran sebagai biaya administrasi atau pendaftaran, tetapi tidak ada bantuan pendidikan yang diberikan kepada korban.",transaction43, reportedAccountJpaRepository.findReportedAccountById(14));
                Reports report44=new Reports("Pelaku mengklaim bahwa korban telah memenangkan hadiah besar dan meminta pembayaran kecil sebagai biaya pengiriman atau administrasi, tetapi tidak ada hadiah yang diterima oleh korban.",transaction44, reportedAccountJpaRepository.findReportedAccountById(15));

                //Tambah untuk nabir
                Reports report45=new Reports("Pelaku mengklaim bahwa korban telah memenangkan hadiah besar dan meminta pembayaran kecil sebagai biaya pengiriman atau administrasi, tetapi tidak ada hadiah yang diterima oleh korban.",transaction55, reportedAccountJpaRepository.findReportedAccountById(10));

                //Tambah untuk rizki
                Reports report46=new Reports("Penipuan hadiah",transaction56, reportedAccountJpaRepository.findReportedAccountById(9));
                Reports report47=new Reports("Pelaku memalsukan penjualan online, menerima pembayaran dari korban, tetapi tidak memberikan produk atau layanan yang dijanjikan.",transaction57, reportedAccountJpaRepository.findReportedAccountById(9));
                Reports report48=new Reports("Saya membeli barang akan tetapi barang tidak dikirim",transaction58, reportedAccountJpaRepository.findReportedAccountById(9));
                Reports report49=new Reports("Pelaku menawarkan investasi palsu dalam kriptokurensi atau skema ponzi yang menghasilkan imbal hasil yang tidak realistis, dan kemudian menghilang dengan dana yang diinvestasikan.",transaction59, reportedAccountJpaRepository.findReportedAccountById(9));
                Reports report50=new Reports("Pelaku menyamar sebagai petugas pelayanan darurat (misalnya polisi, dokter, atau pengacara) dan meminta pembayaran segera untuk biaya pengobatan atau hukum yang tidak ada.",transaction60, reportedAccountJpaRepository.findReportedAccountById(9));
                Reports report51=new Reports("Pelaku menawarkan pekerjaan palsu kepada korban dengan janji gaji yang tinggi. Mereka kemudian meminta korban untuk membayar biaya pendaftaran atau biaya lainnya, yang pada akhirnya digunakan oleh pelaku untuk melakukan transfer uang.",transaction61, reportedAccountJpaRepository.findReportedAccountById(9));
                Reports report52=new Reports("Pelaku menawarkan investasi palsu dalam kriptokurensi atau skema ponzi yang menghasilkan imbal hasil yang tidak realistis, dan kemudian menghilang dengan dana yang diinvestasikan.",transaction62, reportedAccountJpaRepository.findReportedAccountById(9));
                Reports report53=new Reports("Pelaku menawarkan pekerjaan palsu kepada korban dengan janji gaji yang tinggi. Setelah korban menerima \"gaji pertama\", mereka diminta untuk mengirim kembali sebagian uangnya sebagai \"biaya administrasi\", tetapi transfer tersebut sebenarnya ke rekening pelaku.",transaction63, reportedAccountJpaRepository.findReportedAccountById(9));

                reportsJpaRepository.saveAll(Arrays.asList(report1, report2, report3, report4, report5, report6, report7, report8, report9, report10, report11, report12, report13, report14, report15, report16, report17, report18, report19, report20, report21, report22, report23, report24, report25, report26, report27, report28, report29, report30, report31, report32, report33, report34, report35, report36, report37, report38, report39, report40, report41, report42, report43, report44, report45, report46, report47, report48, report49, report50, report51, report52, report53));
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
                Reports report9=reportsJpaRepository.findReportsByReportId(9L);
                Reports report10=reportsJpaRepository.findReportsByReportId(10L);
                Reports report11=reportsJpaRepository.findReportsByReportId(11L);
                Reports report12=reportsJpaRepository.findReportsByReportId(12L);
                Reports report13=reportsJpaRepository.findReportsByReportId(13L);
                Reports report14=reportsJpaRepository.findReportsByReportId(14L);
                Reports report15=reportsJpaRepository.findReportsByReportId(15L);
                Reports report16=reportsJpaRepository.findReportsByReportId(16L);
                Reports report17=reportsJpaRepository.findReportsByReportId(17L);
                Reports report18=reportsJpaRepository.findReportsByReportId(18L);
                Reports report19=reportsJpaRepository.findReportsByReportId(19L);
                Reports report20=reportsJpaRepository.findReportsByReportId(20L);
                Reports report21=reportsJpaRepository.findReportsByReportId(21L);
                Reports report22=reportsJpaRepository.findReportsByReportId(22L);
                Reports report23=reportsJpaRepository.findReportsByReportId(23L);
                Reports report24=reportsJpaRepository.findReportsByReportId(24L);
                Reports report25=reportsJpaRepository.findReportsByReportId(25L);
                Reports report26=reportsJpaRepository.findReportsByReportId(26L);
                Reports report27=reportsJpaRepository.findReportsByReportId(27L);
                Reports report28=reportsJpaRepository.findReportsByReportId(28L);
                Reports report29=reportsJpaRepository.findReportsByReportId(29L);
                Reports report30=reportsJpaRepository.findReportsByReportId(30L);
                Reports report31=reportsJpaRepository.findReportsByReportId(31L);
                Reports report32=reportsJpaRepository.findReportsByReportId(32L);
                Reports report33=reportsJpaRepository.findReportsByReportId(33L);
                Reports report34=reportsJpaRepository.findReportsByReportId(34L);
                Reports report35=reportsJpaRepository.findReportsByReportId(35L);
                Reports report36=reportsJpaRepository.findReportsByReportId(36L);
                Reports report37=reportsJpaRepository.findReportsByReportId(37L);
                Reports report38=reportsJpaRepository.findReportsByReportId(38L);
                Reports report39=reportsJpaRepository.findReportsByReportId(39L);
                Reports report40=reportsJpaRepository.findReportsByReportId(40L);
                Reports report41=reportsJpaRepository.findReportsByReportId(41L);
                Reports report42=reportsJpaRepository.findReportsByReportId(42L);
                Reports report43=reportsJpaRepository.findReportsByReportId(43L);
                Reports report44=reportsJpaRepository.findReportsByReportId(44L);

                Reports report45=reportsJpaRepository.findReportsByReportId(45L);

                Reports report46=reportsJpaRepository.findReportsByReportId(46L);
                Reports report47=reportsJpaRepository.findReportsByReportId(47L);
                Reports report48=reportsJpaRepository.findReportsByReportId(48L);
                Reports report49=reportsJpaRepository.findReportsByReportId(49L);
                Reports report50=reportsJpaRepository.findReportsByReportId(50L);
                Reports report51=reportsJpaRepository.findReportsByReportId(51L);
                Reports report52=reportsJpaRepository.findReportsByReportId(52L);
                Reports report53=reportsJpaRepository.findReportsByReportId(53L);

                ReportAttachment reportAttachment1= new ReportAttachment(report1, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment2= new ReportAttachment(report1, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment3= new ReportAttachment(report2, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment4= new ReportAttachment(report2, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment5= new ReportAttachment(report2, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment6= new ReportAttachment(report3, "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/01/2023/07/19/penipuan-bank-3632286447.jpg");
                ReportAttachment reportAttachment7= new ReportAttachment(report4, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment8= new ReportAttachment(report5, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment9= new ReportAttachment(report5, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment10= new ReportAttachment(report6, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment11= new ReportAttachment(report7, "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/01/2023/07/19/penipuan-bank-3632286447.jpg");
                ReportAttachment reportAttachment12= new ReportAttachment(report8, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment13= new ReportAttachment(report9, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment14= new ReportAttachment(report10, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment15= new ReportAttachment(report11, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment16= new ReportAttachment(report11, "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/01/2023/07/19/penipuan-bank-3632286447.jpg");
                ReportAttachment reportAttachment17= new ReportAttachment(report11, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment18= new ReportAttachment(report12, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment19= new ReportAttachment(report13, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment20= new ReportAttachment(report14, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment21= new ReportAttachment(report15, "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/01/2023/07/19/penipuan-bank-3632286447.jpg");
                ReportAttachment reportAttachment22= new ReportAttachment(report15, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment23= new ReportAttachment(report16, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment24= new ReportAttachment(report16, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment25= new ReportAttachment(report16, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment26= new ReportAttachment(report16, "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/01/2023/07/19/penipuan-bank-3632286447.jpg");
                ReportAttachment reportAttachment27= new ReportAttachment(report17, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment28= new ReportAttachment(report18, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment29= new ReportAttachment(report19, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment30= new ReportAttachment(report20, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment31= new ReportAttachment(report20, "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/01/2023/07/19/penipuan-bank-3632286447.jpg");
                ReportAttachment reportAttachment32= new ReportAttachment(report21, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment33= new ReportAttachment(report22, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment34= new ReportAttachment(report23, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment35= new ReportAttachment(report24, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment36= new ReportAttachment(report24, "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/01/2023/07/19/penipuan-bank-3632286447.jpg");
                ReportAttachment reportAttachment37= new ReportAttachment(report24, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment38= new ReportAttachment(report24, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment39= new ReportAttachment(report24, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment40= new ReportAttachment(report25, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment41= new ReportAttachment(report25, "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/01/2023/07/19/penipuan-bank-3632286447.jpg");
                ReportAttachment reportAttachment42= new ReportAttachment(report26, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment43= new ReportAttachment(report27, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment44= new ReportAttachment(report28, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment45= new ReportAttachment(report29, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment46= new ReportAttachment(report30, "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/01/2023/07/19/penipuan-bank-3632286447.jpg");
                ReportAttachment reportAttachment47= new ReportAttachment(report31, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment48= new ReportAttachment(report31, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment49= new ReportAttachment(report31, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment50= new ReportAttachment(report32, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment51= new ReportAttachment(report32, "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/01/2023/07/19/penipuan-bank-3632286447.jpg");
                ReportAttachment reportAttachment52= new ReportAttachment(report33, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment53= new ReportAttachment(report33, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment54= new ReportAttachment(report34, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment55= new ReportAttachment(report35, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment56= new ReportAttachment(report36, "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/01/2023/07/19/penipuan-bank-3632286447.jpg");
                ReportAttachment reportAttachment57= new ReportAttachment(report37, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment58= new ReportAttachment(report37, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment59= new ReportAttachment(report37, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment60= new ReportAttachment(report37, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment61= new ReportAttachment(report37, "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/01/2023/07/19/penipuan-bank-3632286447.jpg");
                ReportAttachment reportAttachment62= new ReportAttachment(report37, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment63= new ReportAttachment(report37, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment64= new ReportAttachment(report38, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment65= new ReportAttachment(report39, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment66= new ReportAttachment(report40, "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/01/2023/07/19/penipuan-bank-3632286447.jpg");
                ReportAttachment reportAttachment67= new ReportAttachment(report41, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment68= new ReportAttachment(report42, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment69= new ReportAttachment(report42, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment70= new ReportAttachment(report42, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment71= new ReportAttachment(report42, "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/01/2023/07/19/penipuan-bank-3632286447.jpg");
                ReportAttachment reportAttachment72= new ReportAttachment(report43, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment73= new ReportAttachment(report43, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment74= new ReportAttachment(report43, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment75= new ReportAttachment(report43, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment76= new ReportAttachment(report44, "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/01/2023/07/19/penipuan-bank-3632286447.jpg");
                ReportAttachment reportAttachment77= new ReportAttachment(report44, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment78= new ReportAttachment(report44, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment79= new ReportAttachment(report44, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment80= new ReportAttachment(report44, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");

                ReportAttachment reportAttachment81= new ReportAttachment(report45, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment82= new ReportAttachment(report45, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");

                ReportAttachment reportAttachment83= new ReportAttachment(report46, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment84= new ReportAttachment(report47, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment85= new ReportAttachment(report48, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment86= new ReportAttachment(report49, "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/01/2023/07/19/penipuan-bank-3632286447.jpg");
                ReportAttachment reportAttachment87= new ReportAttachment(report50, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment88= new ReportAttachment(report51, "https://panduaji.com/wp-content/uploads/2020/05/Penipuan-Online-Pembeli-576x1024.jpg");
                ReportAttachment reportAttachment89= new ReportAttachment(report52, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");
                ReportAttachment reportAttachment90= new ReportAttachment(report53, "https://cdn.antaranews.com/cache/1200x800/2023/07/25/IMG_20230725_061556.jpg.webp");

                reportAttachmentJpaRepository.saveAll(Arrays.asList(
                        reportAttachment1, reportAttachment2, reportAttachment3, reportAttachment4, reportAttachment5, reportAttachment6, reportAttachment7, reportAttachment8, reportAttachment9, reportAttachment10, reportAttachment11, reportAttachment12, reportAttachment13, reportAttachment14, reportAttachment15, reportAttachment16, reportAttachment17, reportAttachment18, reportAttachment19, reportAttachment20, reportAttachment21, reportAttachment22, reportAttachment23, reportAttachment24, reportAttachment25, reportAttachment26, reportAttachment27, reportAttachment28, reportAttachment29, reportAttachment30, reportAttachment31, reportAttachment32, reportAttachment33, reportAttachment34, reportAttachment35, reportAttachment36, reportAttachment37, reportAttachment38, reportAttachment39, reportAttachment40, reportAttachment41, reportAttachment42, reportAttachment43, reportAttachment44, reportAttachment45, reportAttachment46, reportAttachment47, reportAttachment48, reportAttachment49, reportAttachment50, reportAttachment51, reportAttachment52, reportAttachment53, reportAttachment54, reportAttachment55, reportAttachment56, reportAttachment57, reportAttachment58, reportAttachment59, reportAttachment60, reportAttachment61, reportAttachment62, reportAttachment63, reportAttachment64, reportAttachment65, reportAttachment66, reportAttachment67, reportAttachment68, reportAttachment69, reportAttachment70, reportAttachment71, reportAttachment72, reportAttachment73, reportAttachment74, reportAttachment75, reportAttachment76, reportAttachment77, reportAttachment78, reportAttachment79, reportAttachment80, reportAttachment81, reportAttachment82, reportAttachment83, reportAttachment84, reportAttachment85, reportAttachment86, reportAttachment87, reportAttachment88, reportAttachment89, reportAttachment90
                ));
            }

            if (seedDataEnabled && twitterReportJpaRepository.count() == 0) {
                TwitterReport twitterReport1 = new TwitterReport(LocalDateTime.now(), "SparklingGizmo", "https://twitter.com/SparklingGizmo/status/1481089218322301440", accountService.getAccountByAccountId(1L), null);
                TwitterReport twitterReport2 = new TwitterReport(LocalDateTime.now(), "WhisperingBolt", "https://twitter.com/WhisperingBolt/status/1481089228322483201", accountService.getAccountByAccountId(1L), null);
                TwitterReport twitterReport3 = new TwitterReport(LocalDateTime.now(), "DancingShadow", "https://twitter.com/DancingShadow/status/1481089237732559874", accountService.getAccountByAccountId(4L), null);
                TwitterReport twitterReport4 = new TwitterReport(LocalDateTime.now(), "RadiantSpecter", "https://twitter.com/RadiantSpecter/status/1481089246758125569", accountService.getAccountByAccountId(4L), null);
                TwitterReport twitterReport5 = new TwitterReport(LocalDateTime.now(), "SilentEcho", "https://twitter.com/SilentEcho/status/1481089256474314240", accountService.getAccountByAccountId(4L), null);
                TwitterReport twitterReport6 = new TwitterReport(LocalDateTime.now(), "FrostyBreeze", "https://twitter.com/FrostyBreeze/status/1481089266820577794", accountService.getAccountByAccountId(7L), null);
                TwitterReport twitterReport7 = new TwitterReport(LocalDateTime.now(), "MysticWhisper", "https://twitter.com/MysticWhisper/status/1481089275405636098", accountService.getAccountByAccountId(10L), null);
                TwitterReport twitterReport8 = new TwitterReport(LocalDateTime.now(), "GentleFlame", "https://twitter.com/GentleFlame/status/1481089283937219072", accountService.getAccountByAccountId(10L), null);
                TwitterReport twitterReport9 = new TwitterReport(LocalDateTime.now(), "SilverLining", "https://twitter.com/SilverLining/status/1481089293086769665", accountService.getAccountByAccountId(10L), null);
                TwitterReport twitterReport10 = new TwitterReport(LocalDateTime.now(), "GoldenHarmony", "https://twitter.com/GoldenHarmony/status/1481089301265913860", accountService.getAccountByAccountId(10L), null);
                TwitterReport twitterReport11 = new TwitterReport(LocalDateTime.now(), "VelvetThunder", "https://twitter.com/VelvetThunder/status/1481089311396796422", accountService.getAccountByAccountId(13L), null);
                TwitterReport twitterReport12 = new TwitterReport(LocalDateTime.now(), "WhisperingWhirl", "https://twitter.com/WhisperingWhirl/status/1481089320302861824", accountService.getAccountByAccountId(13L), null);
                TwitterReport twitterReport13 = new TwitterReport(LocalDateTime.now(), "SunnyRipple", "https://twitter.com/SunnyRipple/status/1481089330272817666", accountService.getAccountByAccountId(16L), null);
                TwitterReport twitterReport14 = new TwitterReport(LocalDateTime.now(), "LuminousWhisper", "https://twitter.com/LuminousWhisper/status/1481089339298616320", accountService.getAccountByAccountId(16L), null);
                TwitterReport twitterReport15 = new TwitterReport(LocalDateTime.now(), "GlowingAura", "https://twitter.com/GlowingAura/status/1481089348750715394", accountService.getAccountByAccountId(16L), null);
                TwitterReport twitterReport16 = new TwitterReport(LocalDateTime.now(), "ShimmeringWhirl", "https://twitter.com/ShimmeringWhirl/status/1481089358358157828", accountService.getAccountByAccountId(16L), null);
                TwitterReport twitterReport17 = new TwitterReport(LocalDateTime.now(), "VividSilhouette", "https://twitter.com/VividSilhouette/status/1481089367098959877", accountService.getAccountByAccountId(19L), null);
                TwitterReport twitterReport18 = new TwitterReport(LocalDateTime.now(), "EtherealWhisper", "https://twitter.com/EtherealWhisper/status/1481089376620989442", accountService.getAccountByAccountId(19L), null);
                TwitterReport twitterReport19 = new TwitterReport(LocalDateTime.now(), "TwilightBreeze", "https://twitter.com/TwilightBreeze/status/1481089385682640386", accountService.getAccountByAccountId(19L), null);
                TwitterReport twitterReport20 = new TwitterReport(LocalDateTime.now(), "MysticGlow", "https://twitter.com/MysticGlow/status/1481089394913172993", accountService.getAccountByAccountId(19L), null);
                TwitterReport twitterReport21 = new TwitterReport(LocalDateTime.now(), "DreamyWhisper", "https://twitter.com/DreamyWhisper/status/1481089404323529731", accountService.getAccountByAccountId(19L), null);
                TwitterReport twitterReport22 = new TwitterReport(LocalDateTime.now(), "GlimmeringEcho", "https://twitter.com/GlimmeringEcho/status/1481089414389496322", accountService.getAccountByAccountId(19L), null);
                TwitterReport twitterReport23 = new TwitterReport(LocalDateTime.now(), "LustrousBreeze", "https://twitter.com/LustrousBreeze/status/1481089423369874434", accountService.getAccountByAccountId(22L), null);

                TwitterReport twitterReport24 = new TwitterReport(LocalDateTime.now(), "luvvesoo", "https://twitter.com/luvvesoo/status/1780884334230327325", accountService.getAccountByAccountId(28L), null);
                TwitterReport twitterReport25 = new TwitterReport(LocalDateTime.now(), "jajastipbykiehl", "https://twitter.com/jajastipbykiehl/status/1780866946986373485", accountService.getAccountByAccountId(28L), null);
                TwitterReport twitterReport26 = new TwitterReport(LocalDateTime.now(), "blondefavJ", "https://twitter.com/blondefavJ/status/1777754216909635728", accountService.getAccountByAccountId(28L), null);
                TwitterReport twitterReport27 = new TwitterReport(LocalDateTime.now(), "blondefavJ", "https://twitter.com/blondefavJ/status/1777754216909635728", accountService.getAccountByAccountId(28L), null);
                TwitterReport twitterReport28 = new TwitterReport(LocalDateTime.now(), "nadmisu", "https://twitter.com/nadmisu/status/1776254871685251111", accountService.getAccountByAccountId(28L), null);
                TwitterReport twitterReport29 = new TwitterReport(LocalDateTime.now(), "Annamirg", "https://twitter.com/Annamirg/status/1776205944822935906", accountService.getAccountByAccountId(28L), null);

                TwitterReport twitterReport30 = new TwitterReport(LocalDateTime.now(), "gby_a", "https://twitter.com/gby_a/status/1772473914465177652", accountService.getAccountByAccountId(29L), null);
                TwitterReport twitterReport31 = new TwitterReport(LocalDateTime.now(), "blondefavJ", "https://twitter.com/blondefavJ/status/1776938182141247780", accountService.getAccountByAccountId(29L), null);
                TwitterReport twitterReport32 = new TwitterReport(LocalDateTime.now(), "xlskmingyu", "https://twitter.com/xlskmingyu/status/1776921566363943237", accountService.getAccountByAccountId(29L), null);
                TwitterReport twitterReport33 = new TwitterReport(LocalDateTime.now(), "blondefavJ", "https://twitter.com/blondefavJ/status/1776938182141247780", accountService.getAccountByAccountId(29L), null);
                TwitterReport twitterReport34 = new TwitterReport(LocalDateTime.now(), "gby_a", "https://twitter.com/gby_a/status/1772473914465177652", accountService.getAccountByAccountId(29L), null);
                TwitterReport twitterReport35 = new TwitterReport(LocalDateTime.now(), "straawverrymilk", "https://twitter.com/straawverrymilk/status/1742888379585601609", accountService.getAccountByAccountId(29L), null);

                TwitterReport twitterReport36 = new TwitterReport(LocalDateTime.now(), "jihoonswifeu_", "https://twitter.com/jihoonswifeu_/status/1771459957080142004", accountService.getAccountByAccountId(30L), null);
                TwitterReport twitterReport37 = new TwitterReport(LocalDateTime.now(), "jihoonswifeu_", "https://twitter.com/jihoonswifeu_/status/1756518638599987593", accountService.getAccountByAccountId(30L), null);
                TwitterReport twitterReport38 = new TwitterReport(LocalDateTime.now(), "jihoonswifeu_", "https://twitter.com/jihoonswifeu_/status/1753069719031361979", accountService.getAccountByAccountId(30L), null);
                TwitterReport twitterReport39 = new TwitterReport(LocalDateTime.now(), "jihoonswifeu_", "https://twitter.com/jihoonswifeu_/status/1749291836202795198", accountService.getAccountByAccountId(30L), null);

                TwitterReport twitterReport40 = new TwitterReport(LocalDateTime.now(), "khomsah_", "https://twitter.com/khomsah_/status/1765623972857843749", accountService.getAccountByAccountId(31L), null);
                TwitterReport twitterReport41 = new TwitterReport(LocalDateTime.now(), "khomsah_", "https://twitter.com/khomsah_/status/1765622924747370648", accountService.getAccountByAccountId(31L), null);
                TwitterReport twitterReport42 = new TwitterReport(LocalDateTime.now(), "khomsah_", "https://twitter.com/khomsah_/status/1765622488120320348", accountService.getAccountByAccountId(31L), null);
                TwitterReport twitterReport43 = new TwitterReport(LocalDateTime.now(), "khomsah_", "https://twitter.com/khomsah_/status/1764966235173962147", accountService.getAccountByAccountId(31L), null);

                TwitterReport twitterReport44 = new TwitterReport(LocalDateTime.now(), "jokiwibukuat", "https://twitter.com/jokiwibukuat/status/1762588725211734154", accountService.getAccountByAccountId(32L), null);
                TwitterReport twitterReport45 = new TwitterReport(LocalDateTime.now(), "amlodipinne", "https://twitter.com/amlodipinne/status/1762060954886721947", accountService.getAccountByAccountId(32L), null);
                TwitterReport twitterReport46 = new TwitterReport(LocalDateTime.now(), "amlodipinne", "https://twitter.com/amlodipinne/status/1762060954886721947", accountService.getAccountByAccountId(32L), null);

                TwitterReport twitterReport47 = new TwitterReport(LocalDateTime.now(), "miwmiwa_", "https://twitter.com/miwmiwa_/status/1780802708997607483", accountService.getAccountByAccountId(33L), null);
                TwitterReport twitterReport48 = new TwitterReport(LocalDateTime.now(), "miwmiwa_", "https://twitter.com/miwmiwa_/status/1780802708997607483", accountService.getAccountByAccountId(33L), null);
                TwitterReport twitterReport49 = new TwitterReport(LocalDateTime.now(), "miwmiwa_", "https://twitter.com/miwmiwa_/status/1780802708997607483", accountService.getAccountByAccountId(33L), null);

                TwitterReport twitterReport50 = new TwitterReport(LocalDateTime.now(), "queerisy", "https://twitter.com/queerisy/status/1775933960608211444", accountService.getAccountByAccountId(34L), null);
                TwitterReport twitterReport51 = new TwitterReport(LocalDateTime.now(), "macca138_", "https://twitter.com/macca138_/status/1776100377454338325", accountService.getAccountByAccountId(34L), null);
                TwitterReport twitterReport52 = new TwitterReport(LocalDateTime.now(), "macca138_", "https://twitter.com/macca138_/status/1776100377454338325", accountService.getAccountByAccountId(34L), null);

                TwitterReport twitterReport53 = new TwitterReport(LocalDateTime.now(), "eve_lie", "https://twitter.com/eve_lie/status/1773276485027045781", accountService.getAccountByAccountId(35L), null);
                TwitterReport twitterReport54 = new TwitterReport(LocalDateTime.now(), "doiemoie", "https://twitter.com/doiemoie/status/1768195068404613140", accountService.getAccountByAccountId(35L), null);
                TwitterReport twitterReport55 = new TwitterReport(LocalDateTime.now(), "doiemoie", "https://twitter.com/doiemoie/status/1768195068404613140", accountService.getAccountByAccountId(35L), null);

                TwitterReport twitterReport56 = new TwitterReport(LocalDateTime.now(), "_thisisformark", "https://twitter.com/_thisisformark/status/1737326803445051795", accountService.getAccountByAccountId(36L), null);
                TwitterReport twitterReport57 = new TwitterReport(LocalDateTime.now(), "_thisisformark", "https://twitter.com/_thisisformark/status/1737326803445051795", accountService.getAccountByAccountId(36L), null);
                TwitterReport twitterReport58 = new TwitterReport(LocalDateTime.now(), "woopycakes_", "https://twitter.com/woopycakes_/status/1734607285211766856", accountService.getAccountByAccountId(36L), null);

                TwitterReport twitterReport59 = new TwitterReport(LocalDateTime.now(), "Nitsyyy13", "https://twitter.com/Nitsyyy13/status/1676918034945171456", accountService.getAccountByAccountId(37L), null);
                TwitterReport twitterReport60 = new TwitterReport(LocalDateTime.now(), "Nitsyyy13", "https://twitter.com/Nitsyyy13/status/1676917675916943363", accountService.getAccountByAccountId(37L), null);
                TwitterReport twitterReport61 = new TwitterReport(LocalDateTime.now(), "Nitsyyy13", "https://twitter.com/Nitsyyy13/status/1676917552658923520", accountService.getAccountByAccountId(37L), null);

                TwitterReport twitterReport62 = new TwitterReport(LocalDateTime.now(), "doiemoie", "https://twitter.com/doiemoie/status/1768195068404613140", accountService.getAccountByAccountId(6L), null);
                TwitterReport twitterReport63 = new TwitterReport(LocalDateTime.now(), "doiemoie", "https://twitter.com/doiemoie/status/1768195068404613140", accountService.getAccountByAccountId(6L), null);
                TwitterReport twitterReport64 = new TwitterReport(LocalDateTime.now(), "_thisisformark", "https://twitter.com/_thisisformark/status/1737326803445051795", accountService.getAccountByAccountId(6L), null);
                TwitterReport twitterReport65 = new TwitterReport(LocalDateTime.now(), "_thisisformark", "https://twitter.com/_thisisformark/status/1737326803445051795", accountService.getAccountByAccountId(6L), null);
                TwitterReport twitterReport66 = new TwitterReport(LocalDateTime.now(), "woopycakes_", "https://twitter.com/woopycakes_/status/1734607285211766856", accountService.getAccountByAccountId(6L), null);
                TwitterReport twitterReport67 = new TwitterReport(LocalDateTime.now(), "Nitsyyy13", "https://twitter.com/Nitsyyy13/status/1676918034945171456", accountService.getAccountByAccountId(6L), null);
                TwitterReport twitterReport68 = new TwitterReport(LocalDateTime.now(), "Nitsyyy13", "https://twitter.com/Nitsyyy13/status/1676917675916943363", accountService.getAccountByAccountId(6L), null);
                TwitterReport twitterReport69 = new TwitterReport(LocalDateTime.now(), "Nitsyyy13", "https://twitter.com/Nitsyyy13/status/1676917552658923520", accountService.getAccountByAccountId(6L), null);

                twitterReportJpaRepository.saveAll(Arrays.asList(twitterReport1, twitterReport2, twitterReport3, twitterReport4, twitterReport5, twitterReport6, twitterReport7, twitterReport8, twitterReport9, twitterReport10, twitterReport11, twitterReport12, twitterReport13, twitterReport14, twitterReport15, twitterReport16, twitterReport17, twitterReport18, twitterReport19, twitterReport20, twitterReport21, twitterReport22, twitterReport23, twitterReport24, twitterReport25, twitterReport26, twitterReport27, twitterReport28, twitterReport29, twitterReport30, twitterReport31, twitterReport32, twitterReport33, twitterReport34, twitterReport35, twitterReport36, twitterReport37, twitterReport38, twitterReport39, twitterReport40, twitterReport41, twitterReport42, twitterReport43, twitterReport44, twitterReport45, twitterReport46, twitterReport47, twitterReport48, twitterReport49, twitterReport50, twitterReport51, twitterReport52, twitterReport53, twitterReport54, twitterReport55, twitterReport56, twitterReport57, twitterReport58, twitterReport59, twitterReport60, twitterReport61, twitterReport62, twitterReport63, twitterReport64, twitterReport65, twitterReport66, twitterReport67, twitterReport68, twitterReport69));
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