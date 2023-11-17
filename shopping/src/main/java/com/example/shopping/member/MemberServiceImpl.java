package com.example.shopping.member;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.shopping.cart.CartService;

import groovyjarjarantlr4.v4.parse.BlockSetTransformer.setAlt_return;


@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private CartService cartService;

	@Autowired
	private AmazonS3 amazonS3;
	
	@Value("youjinbucket")
	private String bucketName;
	
	@Override
	public void create(Member member, MultipartFile file) throws IOException {
	File mimg = new File(file.getOriginalFilename());
		
		//aws s3 mulpartfile을 막바로 올리수 없게 되어있다 
		//따라서 파일을 일단 저장한 후에 그 파일을 aws로 올리고 삭제한다. 
		
		try (FileOutputStream fos = new FileOutputStream(mimg)) {
			fos.write(file.getBytes());
		}
		
		//역시 보안등의 이유로 uuid 를 사용해도 좋지만 이번엔 다른 방법을 사용해 보자 
		
		String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		amazonS3.putObject(new PutObjectRequest(bucketName, filename, mimg));
		
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		member.setRole("ROLE_USER");
		member.setCreateDate(LocalDateTime.now());
		member.setMimg(filename);
		
		
		memberRepository.save(member);
		//카트 생성
		cartService.create(member);
		
	}

	@Override
	public List<Member> readlist() {
		return memberRepository.findAll();
	}



	@Override
	public void update(Member member) {
		
		memberRepository.save(member);
		
	}

	@Override
	public void delete(Integer id) {
		Optional<Member> op = memberRepository.findById(id);
		Member member = op.get();
		
		memberRepository.delete(member);
		
	}

	@Override
	public Member readdetailusername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		Optional<Member> om = memberRepository.findByUsername(username);
		Member member = om.get();
		return member;
	}



	

}
