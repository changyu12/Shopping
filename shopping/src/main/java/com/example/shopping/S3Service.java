package com.example.shopping;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

	void uploadFile(MultipartFile multiFile) throws IOException;
}
