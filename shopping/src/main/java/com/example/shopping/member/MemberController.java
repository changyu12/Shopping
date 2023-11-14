package com.example.shopping.member;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.example.shopping.SmsService;

@RequestMapping("/member")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private SmsService smsService;


	@GetMapping("/create")
	public String create(Model model) {
		
		return "member/create";
	}
	
	@PostMapping("/create")
	public String create(Member member, 
						@RequestParam ("memberimg") MultipartFile file) throws NoSuchAlgorithmException, IOException {
		
		memberService.create(member, file);
		
		
		String to = member.getName();
		String phone = member.getTel();
		
		String subject = to+"님의 회원가입을 환영합니다.";
		
		
			smsService.sendSms(subject,phone);
		
		
		
		return "redirect:/login";
	}
	
	@GetMapping("/readlist")
	public String readlist(Model model) {
		model.addAttribute("members", memberService.readlist());
		
		return "member/readlist";
	}

	@GetMapping("/readdetail")
	public String readdetail(Model model) {
		Member member = memberService.readdetailusername();
		model.addAttribute("member", member);

		return "member/readdetail";
	}
	
	
	@PostMapping("/phoneCheck")
	@ResponseBody
	public String phoneCheck(String phone) throws NoSuchAlgorithmException, IOException { // 휴대폰 문자보내기
		int randomNumber = (int)((Math.random()* (9999 - 1000 + 1)) + 1000);//난수 생성
		System.out.println(phone);
		System.out.println(randomNumber);
		String subject = "인증 번호는 "+ randomNumber+"입니다.";
		smsService.sendSms(subject,phone);
		
		return Integer.toString(randomNumber);
	}


}
