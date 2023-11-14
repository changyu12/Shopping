package com.example.shopping.delivery;

import java.time.LocalDateTime;
import java.util.List;

import com.example.shopping.status.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Delivery {
	@Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Integer id;
	   
	   private String uid;  //카드사에서 넘어오는 승인 
	    
	   private String allabout;
	   
	     @OneToMany(mappedBy = "delivery", cascade = CascadeType.REMOVE)
		private List<Status> statusList;
	   
	   private int total;
	   
	   private String username;
	         
	   private LocalDateTime createDate;

}
