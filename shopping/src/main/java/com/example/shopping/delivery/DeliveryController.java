package com.example.shopping.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {
	
	@Autowired
	public DeliveryService deliveryService;
	
	@GetMapping("/payment")
	public String payment(@RequestParam("imp_uid")String imp_uid) {
		
		System.out.println(imp_uid);
		//deliveryService.create(imp_uid);
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
