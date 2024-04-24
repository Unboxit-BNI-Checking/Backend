package com.unboxit.bnichecking.seeder;

import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.model.TwitterReport;
import com.unboxit.bnichecking.repository.AccountJpaRepository;
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
public class TwitterReportSeeder {

    @Autowired
    private TwitterReportJpaRepository twitterReportJpaRepository;

    @Autowired
    private AccountService accountService;

    @Value("${seed.data.enabled:true}")
    private boolean seedDataEnabled;

    @Bean
    public CommandLineRunner twitterReportSeederCommandLineRunner() {
        return args -> {
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


}
