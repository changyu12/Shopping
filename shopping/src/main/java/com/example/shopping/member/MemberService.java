package com.example.shopping.member;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface MemberService {

	void create(Member member, MultipartFile file) throws IOException;

	List<Member> readlist();
	
	Member readdetailusername();
	
	void update(Member member);
	
	void delete(Integer id);
	
}
