package com.example.shopping.product;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

	void create(Product product, MultipartFile file);
	
	List<Product> readlist();
	
	Product readdetail(Integer id);
	
	void update(Product product);
	
	void delete(Integer id);

}
