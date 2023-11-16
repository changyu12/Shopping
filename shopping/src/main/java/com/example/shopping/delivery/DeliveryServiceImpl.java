package com.example.shopping.delivery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.shopping.cart.Cart;
import com.example.shopping.cart.CartService;
import com.example.shopping.item.Item;
import com.example.shopping.item.ItemService;


@Service
public class DeliveryServiceImpl implements DeliveryService {
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ItemService itemService;


	@Override
	public void create(String uid) {
		
		
		 //현재 사용자 정보 추출 
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		 String username = auth.getName();
		 
		 Cart cart = cartService.readdetailusername();
		 List<Item> item = cart.getItemList();
		 String name = item.get(0).getName();

		 String allabout = name ;
		 int total = itemService.findTotalAmount(cart);

		 

	      Delivery delivery = new Delivery();
	      delivery.setCreateDate(LocalDateTime.now());
		  delivery.setAllabout(allabout);
		  delivery.setTotal(total);
	      delivery.setUid(uid);
	      delivery.setUsername(username);
	      deliveryRepository.save(delivery);


}

	@Override
	public List<Delivery> readlist() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		 String username = auth.getName();
		
		 return deliveryRepository.findByUsername(username);
	}

	
	@Override
	public Delivery readdetail(Integer id) {
		 Optional<Delivery> od = deliveryRepository.findById(id);
		 Delivery delivery = od.get();
		 
		 return delivery;


	}

	
	
		
}
