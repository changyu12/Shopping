package com.example.shopping.admin;

import java.util.List;

import com.example.shopping.delivery.Delivery;


public interface AdminService {
	
	List<Delivery>readlist();

    Delivery readdetail(Integer id);


}
