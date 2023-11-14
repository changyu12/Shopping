package com.example.shopping;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.mysql.cj.protocol.Message;

@Service
public class SmsServiceImpl<JSONObject> implements SmsService {
	
	@Override
	public void sendSms(String subject, String phone) throws NoSuchAlgorithmException, IOException {
		
		String charsetType = "UTF-8";
		
	    String sms_url = "";
	    sms_url = "https://sslsms.cafe24.com/sms_sender.php"; // SMS 전송요청 URL
	    String user_id = "austiny"; // SMS아이디
	    String secure = "b05c71225043952fdd34a1a93abaf4ab";//인증키
	    String msg = subject;
	    String rphone = phone;
	    String sphone1 = "010";
	    String sphone2 = "2737";
	    String sphone3 = "3944";
	    String mode = "1";
	    String smsType = "5";
	    
	    
	    // sms 서버 변수값 설정
	    String[] host_info = sms_url.split("/");
	    String host = host_info[2];
	    String path = "/" + host_info[3];
	    int port = 80;


	    
	    // 데이터 맵핑 변수 정의
	    String arrKey[]
	        = new String[] {"user_id","secure","msg", "rphone",
	        					 "sphone1","sphone2","sphone3","mode","smsType"};
	    String valKey[]= new String[arrKey.length] ;
	    valKey[0] = user_id;
	    valKey[1] = secure;
	    valKey[2] = msg;
	    valKey[3] = rphone;
	    valKey[4] = sphone1;
	    valKey[5] = sphone2;
	    valKey[6] = sphone3;
	    valKey[7] = mode;
	    valKey[8] = smsType;
	   

	    String boundary = "";
	    Random rnd = new Random();
	    String rndKey = Integer.toString(rnd.nextInt(32000));
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    byte[] bytData = rndKey.getBytes();
	    md.update(bytData);
	    byte[] digest = md.digest();
	    for(int i =0;i<digest.length;i++)
	    {
	        boundary = boundary + Integer.toHexString(digest[i] & 0xFF);
	    }
	    boundary = "---------------------"+boundary.substring(0,11);

	    // 본문 생성
	    String data = "";
	    String index = "";
	    String value = "";
	    for (int i=0;i<arrKey.length; i++)
	    {
	        index =  arrKey[i];
	        value = valKey[i];
	        data +="--"+boundary+"\r\n";
	        data += "Content-Disposition: form-data; name=\""+index+"\"\r\n";
	        data += "\r\n"+value+"\r\n";
	        data +="--"+boundary+"\r\n";
	    }

	    //out.println(data);

	    InetAddress addr = InetAddress.getByName(host);
	    Socket socket = new Socket(host, port);
	    // 헤더 전송
	    BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), charsetType));
	    wr.write("POST "+path+" HTTP/1.0\r\n");
	    wr.write("Content-Length: "+data.length()+"\r\n");
	    wr.write("Content-type: multipart/form-data, boundary="+boundary+"\r\n");
	    wr.write("\r\n");

	    // 데이터 전송
	    wr.write(data);
	    wr.flush();

	   
	}
	
	
	
	
	
}
