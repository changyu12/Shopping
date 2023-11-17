package com.example.shopping.delivery;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shopping.cart.Cart;
import com.example.shopping.cart.CartRepository;
import com.example.shopping.cart.CartService;
import com.example.shopping.item.ItemService;
import com.example.shopping.member.MemberService;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {
	
	@Autowired
	public DeliveryService deliveryService;
	
	@Autowired
	public CartService cartService;
	
	 @Autowired
	   private ItemService itemService;
	 
	 @Autowired
	   private MemberService memberService;

	@GetMapping("/payment")
	public String payment(String uid, int total) {
		
		 
		
		//System.out.println(imp_uid);
		 deliveryService.create(uid, total);
		
		
		 return "redirect:/delivery/readlist";
	}
	
	@GetMapping("/readlist")
	   public String readlist(Model model) {
		
		 Cart cart = cartService.readdetailusername();

		
	      model.addAttribute("lists", deliveryService.readlist());
	      model.addAttribute("cart", cartService.readdetailusername());
	      model.addAttribute("member", memberService.readdetailusername());
	      model.addAttribute("total", itemService.findTotalAmount(cart));
	      
	      return "delivery/readlist";
	   }

	@GetMapping("/readdetail")
	   public String readdetail(Model model,@RequestParam Integer id) {
	      model.addAttribute("list", deliveryService.readdetail(id));
	      return "delivery/readdetail";
	   }


}
