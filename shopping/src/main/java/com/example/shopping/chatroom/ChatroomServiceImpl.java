package com.example.shopping.chatroom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ChatroomServiceImpl implements ChatroomService {

	@Autowired
	private ChatRoomRepository chatroomRepository;
	
	@Override
	public void create(Chatroom chatroom) {
		
		chatroomRepository.save(chatroom);
	}

	@Override
	public List<Chatroom> readlist() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		return chatroomRepository.findByReceiver(username);
	}

}
