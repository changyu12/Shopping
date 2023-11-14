package com.example.shopping.delivery;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class DeliveryServiceImpl implements DeliveryService {
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Override
	public void create(String uid, String allabout, int total) {
		
		/*
		 * // 현재 사용자 정보 추출 
		 * Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		 * String username = auth.getName();
		 */
		
		String username ="user";
		
		Delivery delivery = new Delivery();
				 delivery.setAllabout(allabout);
				 delivery.setTotal(total);
				 delivery.setUsername(username);
				 delivery.setUid(uid);
		deliveryRepository.save(delivery);

}

	@Override
	public List<Delivery> readlist() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		 String username = auth.getName();
		
		 return deliveryRepository.findByUsername(username);
	}

	@Override
	public List<Delivery> readlistadmin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Delivery readdetail(Integer id) {
		 Optional<Delivery> od = deliveryRepository.findById(id);
		return od.get();
	}
	
	
		
}
