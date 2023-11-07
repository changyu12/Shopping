package com.example.shopping.chatroom;

import java.util.List;

public interface ChatroomService {

	void create(Chatroom chatroom);
	
	List<Chatroom> readlist();
}
