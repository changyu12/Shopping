package com.example.shopping.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shopping.chatreply.ChatreplyService;
import com.example.shopping.member.MemberService;

@RequestMapping("/chat")
@Controller
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ChatreplyService chatreplyService;

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String create(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		model.addAttribute("name", username);
		
		return "chat/create";
	}
	
	
	@PostMapping("/create")
	public String create(Chat chat) {
		
		chatService.create(chat);
		
		return "redirect:/chat/readlist";
	}
	
//	@GetMapping("/readlist")
//	public String readlist(Model model) {
//		
//		model.addAttribute("notices", noticeService.readlist());
//		
//		return "notice/readlist";
//	}
//	
	@GetMapping("/readlist")
	public String readlist(Model model, 
			 @RequestParam(value="page", defaultValue="0") int page) {
		
		Page<Chat> paging = chatService.getList(page);
		model.addAttribute("paging", paging);
		
		return "chat/readlist";
	}
	
	
	
	
	@GetMapping("/readdetail")
	public String readdetail(Model model, 
							 @RequestParam String username) {
		
		
		model.addAttribute("username", username);
		model.addAttribute("chat", chatService.readdetailusername(username));
		
		return "chat/readdetail";
	}
	
	@GetMapping("/update")
	public String update(Model model, 
						 @RequestParam Integer id) {

		model.addAttribute("chat", chatService.readdetail(id));

		return "chat/update";
	}
	
	
	@PostMapping("/update")
	public String update(Chat chat) {
		
		chatService.update(chat);
		
		return "redirect:/chat/readlist";
	}
	
	
	
	
	
	
}
