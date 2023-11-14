package com.example.shopping.item;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.auth.UserDetailRepository;
import com.example.shopping.cart.Cart;
import com.example.shopping.cart.CartService;
import com.example.shopping.member.Member;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.Authentication;

@Service
public class ItemSeviceImpl implements ItemService {

	  @Autowired
	  private ItemRepository itemRepository;
	   @Autowired 
	   private CartService cartService;
	   @Autowired 
	   private UserDetailRepository userDetailRepository;

	   
	   @Override
	   public void create(Item item) {
		 //현재 로그인 하여 사용중인 사람의 정보 추출  
		   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		   String username= auth.getName();

	      Optional<Member> ou = userDetailRepository.findByusername(username);
	      Member member = ou.get();
	      item.setMember(member);
	      
	      Cart cart = cartService.readdetailusername();
	      item.setCart(cart);
	      
	     
	      itemRepository.save(item);
	   }


	@Override
	public Integer findTotalAmount(Cart cart) {
		// TODO Auto-generated method stub
		return itemRepository.findTotalAmount(cart);
	}

	}