package com.example.shopping.product;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.shopping.member.MemberService;


@RequestMapping("/product")
@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private MemberService memberService;

	@GetMapping("/create")
	public String create() {
		return "product/create";
	}
	
	@PostMapping("/create")
	public String create(Product product,
						@RequestParam ("filename") MultipartFile file) throws FileNotFoundException, IOException {
								
		productService.create(product, file);
		return "product/create";
	}
	
    @GetMapping("/readlist")
	public String readlist(Model model) {
		
		model.addAttribute("products", productService.readlist());
		
		return "product/readlist";
	}

	@GetMapping("/readdetail")
	public String readdetail(Model model,@RequestParam("id") Integer id) {
		
		model.addAttribute(memberService.readdetailusername());
		model.addAttribute("product", productService.readdetail(id));
		
		return "product/readdetail";
	}
	
	@GetMapping("/update")
	public String update(Model model, @RequestParam Integer id) {
		model.addAttribute("list",productService.readdetail(id));
		
		return "product/update";
	}
	
	@PostMapping("/update")
	public String update(@RequestParam Integer id,
						 @RequestParam String about,
						 @RequestParam String name,
						 @RequestParam ("filename") MultipartFile file,
						 @RequestParam double price
						 ) {
		
		
	
		productService.update(id, about, name, file, price);
		
		return "redirect:/product/readdetail?id="+id;
	}

	
	@GetMapping("/delete")
	public String delete(@RequestParam ("id") Integer id) {
		
		productService.delete(id);
		
		return "redirect:/product/readlist";
	}
}
