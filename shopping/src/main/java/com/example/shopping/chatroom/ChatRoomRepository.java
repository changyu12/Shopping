package com.example.shopping.chatroom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<Chatroom, Integer> {

	List<Chatroom> findByReceiver(String username);
}
