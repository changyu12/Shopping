package com.example.shopping.chatreply;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.shopping.chat.Chat;
import com.example.shopping.chat.ChatService;
import com.example.shopping.member.MemberService;

@Service
public class ChatreplyServiceImpl implements ChatreplyService{

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatreplyRepository chatreplyRepository;

    @Autowired
    private MemberService memberService;

    @Override
    public void create(Integer id, String content, String username) {
        
        Chat chat = chatService.readdetail(id);
		
        Chatreply c = new Chatreply();
        	c.setUsername(username);
            c.setContent(content);
            c.setChat(chat);
            c.setCreateDate(LocalDateTime.now());
            chatreplyRepository.save(c);

    }

   
	@Override
	public Chatreply readdetail(Integer id) {
		Optional<Chatreply> oc = chatreplyRepository.findById(id);
		return oc.get();
	}
    
}
