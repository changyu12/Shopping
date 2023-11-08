package com.example.shopping.chatreply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RequestMapping("/chatreply")
@Controller
public class ChatreplyController {

    @Autowired
    private ChatreplyService chatreplyService;


    
    @PostMapping("/create")
    public String create(@RequestParam Integer id,
                         @RequestParam String content) {

    	
		
    	chatreplyService.create(id, content);

        return "redirect:/chat/readdetail?id=" + id;
    }





}