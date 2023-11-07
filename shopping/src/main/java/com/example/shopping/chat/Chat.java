package com.example.shopping.chat;

import java.time.LocalDateTime;

import com.example.shopping.chatroom.Chatroom;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String writer;
	
	private String receiver;
	
	private LocalDateTime createDate;
	
	@ManyToOne
	private Chatroom chatroom;
}
