package com.example.shopping.chatroom;

import java.util.List;


import com.example.shopping.chat.Chat;
import com.example.shopping.member.Member;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Chatroom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String writer;
	
	@OneToMany(mappedBy = "chatroom", cascade = CascadeType.REMOVE)
	private List<Chat> chatList;
	
	@ManyToOne
	private Member member;

	
}
