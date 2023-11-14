package com.example.shopping.delivery;

import java.util.List;


public interface DeliveryService {
	
	void create(String uid, String allabout, int total);
	
	List<Delivery> readlist(); //개인 회원 딜리버리 목록 조회용
	
	List<Delivery> readlistadmin(); //관리자용 딜리버리 전체 목록 조회용
	
	Delivery readdetail(Integer id);

}
