package com.unboxit.bnichecking.controller;

import com.unboxit.bnichecking.entity.http.request.CreateReportAttachment;
import com.unboxit.bnichecking.entity.http.response.ApiResponse;
import com.unboxit.bnichecking.entity.http.response.GetAllReportAttachments;
import com.unboxit.bnichecking.model.ReportAttachment;
import com.unboxit.bnichecking.model.Reports;
import com.unboxit.bnichecking.service.ReportAttachmentService;
import com.unboxit.bnichecking.service.ReportsService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReportAttachmentController {

    private final ReportAttachmentService reportAttachmentService;
    private final ReportsService reportsService;

    public ReportAttachmentController(ReportAttachmentService reportAttachmentService, ReportsService reportsService) {
        this.reportAttachmentService = reportAttachmentService;
        this.reportsService = reportsService;
    }

    @PostMapping("/reportAttachment/upload")
    public ResponseEntity<ApiResponse<List<GetAllReportAttachments>>> uploadFile(@RequestParam("reportId") Long reportId, @RequestParam("file") List<MultipartFile> files) throws IOException {
        Reports report= reportsService.getReportsById(reportId);
        if (report == null) {
            ApiResponse<List<GetAllReportAttachments>> response = new ApiResponse<>(false, null, "Report id is invalid");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        List<ReportAttachment> saveAttachment = new ArrayList<>();

        for (MultipartFile file : files) {
            byte[] bytes = file.getBytes();

            String newFileName= reportId.toString() +"_"+file.getOriginalFilename();

            String filePath = "src/main/resources/ReportAttachments/"+newFileName;

            // Create a new file at the specified path
            File newFile = new File(filePath);

            // Write the file bytes to the new file
            // This saves the uploaded file to the specified location
            Files.write(newFile.toPath(), bytes);
            saveAttachment.add(reportAttachmentService.createReportAttachment(new ReportAttachment(report, filePath)));
        }
        List<GetAllReportAttachments> result = new ArrayList<>();
        for(ReportAttachment a : saveAttachment){
            result.add(new GetAllReportAttachments(a.getReportAttachmentId(), a.getReportId().getReportId(), a.getFilePath(), a.getCreatedAt()));
        }
        return ResponseEntity.ok(new ApiResponse<>(true, result, null));
    }

    @GetMapping("/reportAttachment/report/{report_id}")
    public ResponseEntity<ApiResponse<List<GetAllReportAttachments>>> getListReportAttachments(@PathVariable Long report_id){
        if(reportAttachmentService.findReportAttachmentByReportId(report_id)==null){
            ApiResponse<List<GetAllReportAttachments>> response = new ApiResponse<>(true, null, "Report with this report id has no Attachments");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        List<GetAllReportAttachments> reportAttachments = reportAttachmentService.findReportAttachmentByReportId(report_id);
        return ResponseEntity.ok(new ApiResponse<>(true, reportAttachments, null));
    }

    //downloadAttachmentFile
    @GetMapping("/reportAttachment/{report_attachment_id}")
    public ResponseEntity<Resource> getAttachmentFile(@PathVariable Long report_attachment_id) throws IOException {

        ReportAttachment reportAttachment= reportAttachmentService.findReportAttachmentByReportAttachmentId(report_attachment_id);
        // Load file as Resource
        Path filePath = Paths.get(reportAttachment.getFilePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(filePath));
//        String newFileName = resource.getFilename();

        // Set content type as application/octet-stream
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filePath);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        // Return ResponseEntity
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(Files.size(filePath))
                .body(resource);
    }
}
