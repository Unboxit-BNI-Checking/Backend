package com.unboxit.bnichecking.seeder;

import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.model.Transaction;
import com.unboxit.bnichecking.model.TwitterReport;
import com.unboxit.bnichecking.repository.AccountJpaRepository;
import com.unboxit.bnichecking.repository.TransactionJpaRepository;
import com.unboxit.bnichecking.repository.TwitterReportJpaRepository;
import com.unboxit.bnichecking.service.AccountService;
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
@DependsOn("accountSeederCommandLineRunner")
public class TransactionSeeder {

    @Autowired
    private TransactionJpaRepository transactionJpaRepository;

    @Autowired
    private AccountService accountService;

    @Value("${seed.data.enabled:true}")
    private boolean seedDataEnabled;


    @Bean
    public CommandLineRunner transactionSeederCommandLineRunner() {
        return args -> {
            if (seedDataEnabled && transactionJpaRepository.count() == 0) {
                Account sourceAccount = accountService.getAccountByAccountId(1L);
                Account destinationAccount = accountService.getAccountByAccountId(2L);
                Transaction transaction1 = new Transaction(sourceAccount, destinationAccount, 120000L, null);
                Transaction transaction2 = new Transaction(sourceAccount, destinationAccount, 12000L, "test transfer");
                Transaction transaction3 = new Transaction(sourceAccount, destinationAccount, 20000L, "test lagi");

                transactionJpaRepository.saveAll(Arrays.asList(transaction1, transaction2, transaction3));
            }
        };
    }


}
