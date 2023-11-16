package com.example.shopping.item;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/item")
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	 @PostMapping("/create")
	   public String create(@RequestParam Integer id,
	                      @RequestParam String name,
	                      @RequestParam String productid,
	                      @RequestParam double price){
	      Item item = new Item();
	      item.setName(name);
	      item.setPrice(price);
	      item.setProductId(productid);
	      item.setCreateDate(LocalDateTime.now());

	      itemService.create(item);
	      
	      return "redirect:/product/readdetail?id=" +id;
	   }
	 
	    @GetMapping("/delete")
	    public String showDeleteForm(@RequestParam Integer id) {
	        
	    	 itemService.deleteById(id);
	    	
	        return "redirect:/cart/readdetail";
	    }
	}





