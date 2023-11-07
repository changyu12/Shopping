package com.example.shopping.notice;

import java.util.List;

import org.springframework.data.domain.Page;

public interface NoticeService {

	void create(Notice notice);
	
	List<Notice> readlist();
	
	Notice readdetail(Integer id);
	
	void update(Notice notice);
	
	void delete(Integer id);
	
	Page<Notice> getList(int page);
}
