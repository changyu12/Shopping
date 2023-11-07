package com.example.shopping.chatroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cartexam.cart.Cart;



@RequestMapping("/chatroom")
@Controller
public class ChatroomController {

	@Autowired
	private ChatroomService chatroomService;
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String create(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		model.addAttribute("name", username);

		return "chatroom/create";
	}

	@PostMapping("/create")
	public String create(Chatroom chatroom) {

		chatroomService.create(chatroom);
		
		return "redirect:/chatroom/readlist";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/readlist")
	public String readlist(Model model) {
		
		model.addAttribute("chatlist", chatroomService.readlist());
		
		return"chatroom/readlist";
	}
	
	@GetMapping("/readdetail")
	public String readdetail(Model model) {
		
		
		
		Chat chat = ChatService.readdetailusername();
		
		model.addAttribute("user",userService.readdetailusername());
		model.addAttribute("list", cartService.readdetailusername());
		model.addAttribute("total", itemService.findTotalAmount(cart));
		
		return "cart/readdetail";
	}

	

}
