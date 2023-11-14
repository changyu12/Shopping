package com.example.shopping;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface SmsService {

	void sendSms(String subject, String phone ) throws NoSuchAlgorithmException, IOException;
	
}
