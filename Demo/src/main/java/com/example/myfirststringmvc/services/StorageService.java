package com.example.myfirststringmvc.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service //bean trong ioc
public class StorageService {
    private final Path uploadDir;
    public StorageService() {
        //xác định thư mục lưu trữ file cố định
        //user.dir + uploads
        this.uploadDir = Path.of(System.getProperty("user.dir"),"uploads");
        try{
            Files.createDirectories(uploadDir); //tạo thư mục nếu upload chưa tồn tại
        }catch (IOException e ){
            throw new RuntimeException("Không tạo được",e);
        }
    }
    public String saveFile(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
       //Random uuid + đuôi file
        String fileName = file.getOriginalFilename();
        Path path = uploadDir.resolve(file.getOriginalFilename()).normalize().toAbsolutePath();
        Files.write(path, bytes, StandardOpenOption.CREATE);
        return "/uploads/"+ fileName ;
    }
}
