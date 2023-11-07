package com.example.shopping.notice;

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

import com.example.shopping.member.Member;
import com.example.shopping.member.MemberService;

@RequestMapping("/notice")
@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private MemberService memberService;

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String create(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		model.addAttribute("name", username);
		
		return "notice/create";
	}
	
	
	@PostMapping("/create")
	public String create(Notice notice) {
		
		noticeService.create(notice);
		
		return "redirect:/notice/readlist";
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
		
		Page<Notice> paging = noticeService.getList(page);
		model.addAttribute("paging", paging);
		
		return "notice/readlist";
	}
	
	
	
	
	@GetMapping("/readdetail")
	public String readdetail(Model model, 
							 @RequestParam Integer id) {
		
		Notice notice = noticeService.readdetail(id);
		
		model.addAttribute("notice", noticeService.readdetail(id));
		
		return "notice/readdetail";
	}
	
	@GetMapping("/update")
	public String update(Model model, 
						 @RequestParam Integer id) {

		model.addAttribute("notice", noticeService.readdetail(id));

		return "notice/update";
	}
	
	
	@PostMapping("/update")
	public String update(Notice notice) {
		
		noticeService.update(notice);
		
		return "redirect:/notice/readlist";
	}
	
	
	
	
	
	
}
