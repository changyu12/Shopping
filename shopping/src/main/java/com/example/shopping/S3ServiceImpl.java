package com.example.shopping;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Member;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3ServiceImpl implements S3Service {

	@Autowired
	private AmazonS3 amazonS3;
	
	@Value("bucket-fgn7ek")
	private String bucketName;

	@Override
	public void uploadFile(MultipartFile multipartFile) throws IOException {
		
		File file = new File(multipartFile.getOriginalFilename());
		
		//aws s3 mulpartfile을 막바로 올리수 없게 되어있다 
		//따라서 파일을 일단 저장한 후에 그 파일을 aws로 올리고 삭제한다. 
		
		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(multipartFile.getBytes());
		}
		
		//역시 보안등의 이유로 uuid 를 사용해도 좋지만 이번엔 다른 방법을 사용해 보자 
		
		String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
		amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file));
		

		//파일을 s3로 올리고 서버에 저장했던 파일은 이제 완전히 삭제한다. 
		file.delete();
		
	}

}
