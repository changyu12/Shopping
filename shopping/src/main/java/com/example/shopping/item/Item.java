package com.example.shopping.item;

import java.time.LocalDateTime;

import com.example.shopping.cart.Cart;
import com.example.shopping.member.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String username;

	private String name;
	
	private double price;
	
	private String productId;
	
	private LocalDateTime createDate;
	
	private String pimg; //상품 사진

	
	@ManyToOne
	private Cart cart;


}
