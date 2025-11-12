package com.example.casestudy.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
public class StorageService {
    private final Path uploadDir;

    public StorageService() {
        this.uploadDir = Path.of(System.getProperty("user.dir"), "uploadCaseStudy");
        try {
            Files.createDirectories(uploadDir); //tạo thư mục nếu folder chưa tồn tại
        } catch (IOException e) {
            throw new RuntimeException("Not able to create directory", e);
        }
    }
    public String saveFile(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        //Random uuid + đuôi file
        String fileName = file.getOriginalFilename();
        Path path = uploadDir.resolve(file.getOriginalFilename()).normalize().toAbsolutePath();
        Files.write(path, bytes, StandardOpenOption.CREATE);
        return "/uploadCaseStudy/"+ fileName ;
    }
}
