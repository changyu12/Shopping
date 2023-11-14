package com.example.shopping.member;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.shopping.cart.CartService;

import groovyjarjarantlr4.v4.parse.BlockSetTransformer.setAlt_return;


@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private CartService cartService;

	@Override
	public void create(Member member, MultipartFile file) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		
		String uuid = UUID.randomUUID().toString();
		
		String filename = uuid + "_" + file.getOriginalFilename();;
		
		String FTP_ADDRESS = "iup.cdn1.cafe24.com";
				String LOGIN = "chandool";
				String PSW = "rb123123!";
				
				FTPClient con = null;
				
				try {
					con = new FTPClient();
					con.connect(FTP_ADDRESS);
					
					if(con.login(LOGIN, PSW)) {
						con.enterLocalPassiveMode();
						con.setFileType(FTP.BINARY_FILE_TYPE);
						con.storeFile(filename, file.getInputStream());
						con.logout();
						con.disconnect();
						System.out.println("success!!!");
					}
				} catch (Exception e) {
					System.out.println("fail!!!");
				}
		
		
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
