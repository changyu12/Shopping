package com.example.shopping.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/member")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@GetMapping("/create")
	public String create() {
		return "member/create";
	}
	
	@PostMapping("/create")
	public String create(Member member, 
						@RequestParam ("memberimg") MultipartFile file) {
		
		memberService.create(member, file);
		
		return "redirect:/login";
	}
	
	@GetMapping("/readlist")
	public String readlist(Model model) {
		model.addAttribute("members", memberService.readlist());
		
		return "member/readlist";
	}
	
	@GetMapping("/readdetail")
	public String readdetail(Model model, @RequestParam("id") Integer id) {
		
		model.addAttribute("profile", memberService.readdetail(id));
		
		return "member/readdetail";
	}


}
