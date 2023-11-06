package com.example.shopping.member;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface MemberService {

	void create(Member member, MultipartFile file);

	List<Member> readlist();
	
	Member readdetail(Integer id);
	
	void update(Member member);
	
	void delete(Integer id);
}
