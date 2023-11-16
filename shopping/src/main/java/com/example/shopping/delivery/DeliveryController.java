package com.example.shopping.delivery;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shopping.cart.Cart;
import com.example.shopping.cart.CartRepository;
import com.example.shopping.item.Item;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {
	
	@Autowired
	public DeliveryService deliveryService;
	
	@Autowired
	public CartRepository cartRepository;
	
	@GetMapping("/payment")
	public String payment(@RequestParam("imp_uid")String imp_uid) {
		
		System.out.println(imp_uid);
		//deliveryService.create(imp_uid);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		Optional<Cart> oc = cartRepository.findByUsername(username);
		List<Item> item = oc.get().getItemList();
	 
		
		
		
		
		return"";
	}
	
	@GetMapping("/readlist")
	   public String readlist(Model model) {
	      model.addAttribute("lists", deliveryService.readlist());
	      
	      return "delivery/readlist";
	   }

	@GetMapping("/readdetail")
	   public String readdetail(Model model,@RequestParam Integer id) {
	      model.addAttribute("list", deliveryService.readdetail(id));
	      return "delivery/readdetail";
	   }


}
