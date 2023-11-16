package com.example.shopping.item;

import com.example.shopping.cart.Cart;

public interface ItemService {
	
	void create (Item item);
	
	Integer findTotalAmount(Cart cart);
	
	void deleteById(Integer id);
	
}
