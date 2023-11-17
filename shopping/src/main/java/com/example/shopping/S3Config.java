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
	
	@Value("AKIAZI6DOJRGVMKOINUB")
	private String awsAccessKey;	// AKIAZI6DOJRGVMKOINUB
	
	@Value("5daiOmvMYo5/xwXuARSpG6u3iRlKA0OD4idbjiy2")
	private String awsSecretKey;	// 5daiOmvMYo5/xwXuARSpG6u3iRlKA0OD4idbjiy2
	
	@Value("ap-northeast-2")
	private String region;			// ap-northeast-2
	
	
		 @Bean
		 public AmazonS3 s3client() {
		        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
		        return AmazonS3ClientBuilder.standard()
		                .withRegion(Regions.fromName(region))
		                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
		                .build();
		    }
	
}
