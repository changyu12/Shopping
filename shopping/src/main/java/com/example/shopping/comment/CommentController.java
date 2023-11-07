package com.example.shopping.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RequestMapping("/comment")
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


    
    @PostMapping("/create")
    public String create(@RequestParam Integer id,
                         @RequestParam String content) {

            commentService.create(id, content);

        return "redirect:/notice/readdetail?id=" + id;
    }





}