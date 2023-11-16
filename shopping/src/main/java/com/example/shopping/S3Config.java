package com.example.shopping;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;


@Configuration
public class S3Config {

	@Value("AKIARIAQQ6WOWDLV3E6W")
	private String awsAccessKey;  // AKIAV2KG7UAVDGLQJ3FZ
	
	@Value("8DR++8zDPYGQMsUeYaO7GrVNg7y5of+U/B6wYxY0")
	private String awsSecretKey;  // BDeHGrNqEjf2Q9SsJpcYBOGBQIw0iguLXs/x+gfO
	
	@Value("ap-northeast-2")
	private String region;        // ap-northeast-2
	
    @Bean
    public AmazonS3 s3client() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }
}
	
	