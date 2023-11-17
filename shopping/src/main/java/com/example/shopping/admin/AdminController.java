package com.example.shopping.admin;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shopping.delivery.Delivery;
import com.example.shopping.delivery.DeliveryRepository;
import com.example.shopping.delivery.DeliveryService;
import com.example.shopping.status.Status;
import com.example.shopping.status.StatusRepository;


@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	@Autowired
	private DeliveryService deliveryService;
	
	@GetMapping("/deliveryreadlist")
	public String readlist(Model model) {
		model.addAttribute("lists", adminService.readlist());
		
		return"admin/deliveryreadlist";
	}
	

	@GetMapping("/deliveryreaddetail")
	   public String readdetail(Model model,@RequestParam Integer id) {
	      model.addAttribute("list", adminService.readdetail(id));
	      model.addAttribute("list", deliveryService.readdetail(id));
	      
	      return "admin/deliveryreaddetail";
	   }
	
	 @PostMapping("/create")
	   public String create(@RequestParam Integer id,
			                @RequestParam String username,
					        @RequestParam String sno,
						    @RequestParam String step) {
							
		 
		 Status status = new Status();
		 
		 Optional<Delivery> od = deliveryRepository.findById(id);
	      
	      status.setCreateDate(LocalDateTime.now());
	      status.setDelivery(od.get());
	      status.setUsername(username);
	      status.setSno(sno);
	      status.setStep(step);
	      statusRepository.save(status);
	      
	      return "redirect:/admin/deliveryreaddetail?id=" + id;
	   }


}
