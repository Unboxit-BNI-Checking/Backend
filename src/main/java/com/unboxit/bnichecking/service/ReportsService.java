package com.unboxit.bnichecking.service;

import com.unboxit.bnichecking.entity.db.GetReportsAndTransactionByCustomerName;
import com.unboxit.bnichecking.entity.http.response.*;
import com.unboxit.bnichecking.model.ReportAttachment;
import com.unboxit.bnichecking.model.Reports;
import com.unboxit.bnichecking.repository.ReportsJpaRepository;
import com.unboxit.bnichecking.util.AttachmentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportsService {
    @Autowired
    private ReportsJpaRepository reportsJpaRepository;
    private AccountService accountService;
    private ReportAttachmentService reportAttachmentService;

    @Autowired
    private AttachmentService attachmentService;

    @PersistenceContext
    private EntityManager entityManager;

    public ReportsService(ReportsJpaRepository reportsJpaRepository, AccountService accountService, ReportAttachmentService reportAttachmentService) {
        this.reportsJpaRepository = reportsJpaRepository;
        this.accountService = accountService;
        this.reportAttachmentService = reportAttachmentService;
    }

    public List<GetAllReports> getReports(){
        List<GetAllReports> results = new ArrayList<>();
        List<Reports> reports = reportsJpaRepository.findAll();

        for (Reports report : reports) {
            GetAllReports getAllReports = new GetAllReports();
            getAllReports.setReportId(report.getReportId());
            getAllReports.setTransactionId(report.getTransaction().getTransactionId());
            getAllReports.setChronology(report.getChronology());
            getAllReports.setReportedAccountId(report.getReportedAccount().getReportedAccountId());
            results.add(getAllReports);
        }

        return results;
    }

    public List<Reports> getReportsByReportedAccountIdToReports(long reportedAccount_Id){
        return reportsJpaRepository.findReportsByReportedAccountId(reportedAccount_Id);
    }

    public List<GetAllReports> getReportsByReportedAccountId(long reportedAccount_Id){
        List<GetAllReports> results = new ArrayList<>();
        List<Reports> reports = reportsJpaRepository.findReportsByReportedAccountId(reportedAccount_Id);

        for (Reports report : reports) {
            GetAllReports getAllReports = new GetAllReports();
            getAllReports.setReportId(report.getReportId());
            getAllReports.setTransactionId(report.getTransaction().getTransactionId());
            getAllReports.setChronology(report.getChronology());
            results.add(getAllReports);
        }

        return results;
    }

    public List<GetReportsAndTransactionByCustomerName> getReportsAndTransactionByCustomerNames(String accountUsername){
        List<Object[]> results = reportsJpaRepository.findReportsAndTransactionByAccountName(accountUsername);
        List<GetReportsAndTransactionByCustomerName> resultList = new ArrayList<>();

        for (Object[] result : results) {
            GetReportsAndTransactionByCustomerName reportsAndTransactionByCustomerName = new GetReportsAndTransactionByCustomerName();
            reportsAndTransactionByCustomerName.setReportsId((long) result[0]);
            reportsAndTransactionByCustomerName.setCreatedAtReports(((Timestamp) result[1]).toLocalDateTime());
            reportsAndTransactionByCustomerName.setStatus((long) result[2]);
            reportsAndTransactionByCustomerName.setAccountNumberSource((String) result[3]);
            reportsAndTransactionByCustomerName.setAccountNumberSourceUsername(accountService.getAccountByAccountNumber((String) result[3]).getUserId().getCustomerName());
            reportsAndTransactionByCustomerName.setAccountNumberDestination((String) result[4]);
            reportsAndTransactionByCustomerName.setAccountNumberDestinationUsername(accountService.getAccountByAccountNumber((String) result[4]).getUserId().getCustomerName());
            reportsAndTransactionByCustomerName.setAmount((long) result[5]);
            reportsAndTransactionByCustomerName.setCreatedAtTransaction(((Timestamp) result[6]).toLocalDateTime());
            reportsAndTransactionByCustomerName.setChronology((String) result[7]);
            List<GetAllReportAttachments> reportAttachment = reportAttachmentService.findReportAttachmentByReportId(reportsAndTransactionByCustomerName.getReportsId());
            reportsAndTransactionByCustomerName.setAttachment(reportAttachment.get(0).getFilePath());
            resultList.add(reportsAndTransactionByCustomerName);
        }
        return resultList;
    }

    public Reports getReportsById(Long id) {
        return reportsJpaRepository.findById(id).get();
    }

    public CreateReportResponse createReportsAndAttachment(Long TransactionId, String chronology, List<MultipartFile> files) throws IOException {
        CreateReportResponse result = new CreateReportResponse();
        result.setChronology(chronology);

        Query query = entityManager.createNativeQuery("SELECT transaction_id, account_number_destination FROM transactions WHERE transaction_id = ?");
        Query query2 = entityManager.createNativeQuery("SELECT reported_account_id, reported_account_number, status, created_at FROM reported_account WHERE reported_account_number = ?");
        Query query3 = entityManager.createNativeQuery("SELECT reported_account_id, reported_account_number, status, created_at FROM reported_account WHERE status = ? AND reported_account_number = ?");

        //get Transaction id and account number destination
        query.setParameter(1, TransactionId);
        List<Object[]> resultList = query.getResultList();

        GetTransactionFromReports transaction = new GetTransactionFromReports();
        List<Long> listStatus = new ArrayList<>();
        List<Object[]> result3;

        if (!resultList.isEmpty()) {
            Object[] resultTransaction = resultList.get(0);
            transaction.setTransactionId(((Number) resultTransaction[0]).longValue());
            transaction.setAccountNumberDestination((String) resultTransaction[1]);
            result.setTransactionId(transaction.getTransactionId());
            query2.setParameter(1, transaction.getAccountNumberDestination());
            List<Object[]> resultList2 = query2.getResultList();

            if (!resultList2.isEmpty()) {
                for (Object[] obj : resultList2) {
                    GetReportedAccount reportedAccount = new GetReportedAccount();
                    reportedAccount.setStatus(((Number) obj[2]).longValue());
                    listStatus.add(reportedAccount.getStatus());
                }
                if (listStatus.contains((long) 1)) {
                    query3.setParameter(1, (long) 1);
                    query3.setParameter(2, transaction.getAccountNumberDestination());
                    result3 = query3.getResultList();
                    for (Object[] row : result3) {
                        result.setReportedAccountId(((Number) row[0]).longValue());
                    }
                    result.setCreateAt(LocalDateTime.now());
                    reportsJpaRepository.insertReports(result.getTransactionId(), result.getReportedAccountId(), result.getChronology(), result.getCreateAt());
                    Long newReportsId = reportsJpaRepository.findReportIdByNewReport(result.getTransactionId(), result.getReportedAccountId(), result.getChronology(), result.getCreateAt());
                    Reports report = getReportsById(newReportsId);

                    for (MultipartFile file : files) {
                        String url = attachmentService.saveAttachment(file);
                        reportAttachmentService.createReportAttachment(new ReportAttachment(report, url));
                    }
                    return result;
                } else {
                    createAndInsertReportedAccountUpload(transaction, listStatus, result, query3, query2, files);
                }
            } else {
                createAndInsertReportedAccountUpload(transaction, listStatus, result, query3, query2, files);
            }
        }
        return result;
    }

    private CreateReportResponse createAndInsertReportedAccountUpload(GetTransactionFromReports transaction, List<Long> listStatus, CreateReportResponse result, Query query3, Query query2, List<MultipartFile> files) throws IOException {
        reportsJpaRepository.insertReportedAccount(transaction.getAccountNumberDestination(), (long) 1, LocalDateTime.now());
        query2.setParameter(1, transaction.getAccountNumberDestination());
        List<Object[]> resultList2 = query2.getResultList();
        for (Object[] obj : resultList2) {
            GetReportedAccount reportedAccount = new GetReportedAccount();
            reportedAccount.setStatus(((Number) obj[2]).longValue());
            listStatus.add(reportedAccount.getStatus());
        }
        for (Long status : listStatus) {
            if (status == 1) {
                query3.setParameter(1, status);
                query3.setParameter(2, transaction.getAccountNumberDestination());
                List<Object[]> result3 = query3.getResultList();
                for (Object[] row : result3) {
                    result.setReportedAccountId(((Number) row[0]).longValue());
                }
            }
        }
        result.setCreateAt(LocalDateTime.now());
        reportsJpaRepository.insertReports(result.getTransactionId(), result.getReportedAccountId(), result.getChronology(), result.getCreateAt());
        Long newReportsId = reportsJpaRepository.findReportIdByNewReport(result.getTransactionId(), result.getReportedAccountId(), result.getChronology(), result.getCreateAt());
        Reports report = getReportsById(newReportsId);

        for (MultipartFile file : files) {
            String url = attachmentService.saveAttachment(file);
            reportAttachmentService.createReportAttachment(new ReportAttachment(report, url));
        }
        return result;
    }

    public Long countReports(){
        return reportsJpaRepository.count();
    }

    public long countByReportedAccount_Status(Long status) {
        System.out.println(reportsJpaRepository.countReportedByStatus(status));
        System.out.println(status);
        return reportsJpaRepository.countReportedByStatus(status);
    }

    public List<GetTotalReportCompleted> getTotalReportCompletedByMonth() {
        List<Object[]> resultlist = reportsJpaRepository.getTotalReportByMonth();
        List<GetTotalReportCompleted> res = new ArrayList<>();
        for (Object[] obj : resultlist) {
            GetTotalReportCompleted getTotalReportCompletedByMonth= new GetTotalReportCompleted();
            getTotalReportCompletedByMonth.getBulan((String) obj[0]);
            getTotalReportCompletedByMonth.setJumlah((Long) obj[1]);
            res.add(getTotalReportCompletedByMonth);
        }
        return res;
    }
}