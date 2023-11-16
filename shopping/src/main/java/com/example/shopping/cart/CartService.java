package com.example.shopping.cart;

import com.example.shopping.member.Member;

public interface CartService {
	
	Cart readdetailusername();
	
	void create(Member member);
	
	void delete(Integer id);


}
