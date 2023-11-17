package com.example.shopping.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.delivery.Delivery;
import com.example.shopping.delivery.DeliveryRepository;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Override
	public List<Delivery> readlist() {
		
		return deliveryRepository.findAll();
	}

	@Override
	public Delivery readdetail(Integer id) {
		Optional<Delivery> od = deliveryRepository.findById(id);
		return od.get();
	}

}
