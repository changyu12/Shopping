package com.example.shopping.delivery;

import java.util.List;


public interface DeliveryService {
	
	void create(String uid, int total);
	
	List<Delivery> readlist(); //개인 회원 딜리버리 목록 조회용
	

	Delivery readdetail(Integer id);


	
	

}
