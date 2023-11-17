package com.example.shopping.item;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.shopping.cart.Cart;
import com.example.shopping.cart.CartService;


import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.Authentication;

@Service
public class ItemServiceImpl implements ItemService {

	  @Autowired
	  private ItemRepository itemRepository;
	   @Autowired 
	   private CartService cartService;
	   
	  

	   
	   @Override
	   public void create(Item item) {
		 //현재 로그인 하여 사용중인 사람의 정보 추출  
		   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		   String username= auth.getName();

	      item.setUsername(username);
	      
	      Cart cart = cartService.readdetailusername();
	      item.setCart(cart);
	      
	     
	      itemRepository.save(item);
	   }


	@Override
	public Integer findTotalAmount(Cart cart) {
		// TODO Auto-generated method stub
		return itemRepository.findTotalAmount(cart);
	}


	@Override
	public void deleteById(Integer id) {
		 itemRepository.deleteById(id);
		
	}

	}