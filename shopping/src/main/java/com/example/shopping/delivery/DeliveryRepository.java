package com.example.shopping.delivery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
	

	List<Delivery> findByUsername(String username);

}
