package com.example.shopping.product;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //primary
	
	private String name; //상품 이름
	
	private double price; //상품 가격
	
	@Column(columnDefinition = "LONGTEXT")
	private String about; //상품 설명
	
	private String productid; //상품 번호
	
	private String pimg; //상품 사진
	
	private String feed; //제품문의 대화방
	
	private LocalDateTime createDate; 
	
	

}
