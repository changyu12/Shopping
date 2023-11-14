package com.example.shopping.status;

import java.time.LocalDateTime;

import com.example.shopping.delivery.Delivery;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Status {
	@Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Integer id;
	   
	   private String sno; //송장번호
	   
	   private int step;
	   
	   private LocalDateTime createDate;
	   
	   
	   @ManyToOne
	   private Delivery delivery;
	   
	   private String username;



	
	
}
