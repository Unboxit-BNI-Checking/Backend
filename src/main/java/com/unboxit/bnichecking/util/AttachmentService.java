package com.unboxit.bnichecking.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

@Service
public class AttachmentService {
    @Value("${gcp.config.file}")
    String gcpConfigFile;

    @Value("${gcp.project.id}")
    String gcpProjectId;

    public String saveAttachment(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null) {
            throw new RuntimeException("something has exploded: file name not found");
        }

        Path path = new File(originalFileName).toPath();
        try {
            String contentType = Files.probeContentType(path);
            File convertedFile = new File(file.getOriginalFilename());
            FileOutputStream outputStream = new FileOutputStream(convertedFile);
            outputStream.write(file.getBytes());
            outputStream.close();

            byte[] fileData = FileUtils.readFileToByteArray(convertedFile);
            InputStream inputStream = new ClassPathResource(gcpConfigFile).getInputStream();

            StorageOptions options = StorageOptions.newBuilder().setProjectId(gcpProjectId)
                    .setCredentials(GoogleCredentials.fromStream(inputStream)).build();

            Storage storage = options.getService();
            Bucket bucket = storage.get("unboxit-report-attachments", Storage.BucketGetOption.fields());
            Blob blob = bucket.create( originalFileName, fileData, contentType);
            BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("unboxit-report-attachments", blob.getName())).build();
            URL url = storage.signUrl(blobInfo, 3650, TimeUnit.DAYS, Storage.SignUrlOption.withV2Signature());
            
            convertedFile.delete();
            return url.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
