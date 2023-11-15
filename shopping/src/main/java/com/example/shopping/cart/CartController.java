package com.example.shopping.cart;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.shopping.item.ItemService;
import com.example.shopping.member.MemberService;



@RequestMapping("/cart")
@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("readdetail")
	public String readdetail(Model model) {
		
	
		Cart cart = cartService.readdetailusername();
		
		model.addAttribute("user", memberService.readdetailusername());
		model.addAttribute("list", cartService.readdetailusername());
		model.addAttribute(	"total",itemService.findTotalAmount(cart));
		return"cart/readdetail";
	}
	
	@GetMapping("/delete")
	public String delete() {
		
		cartService.delete();
		
		return "redirect:/cart/readdetail";
	}
}
