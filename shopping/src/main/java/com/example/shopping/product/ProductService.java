package com.example.shopping.product;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

	void create(Product product, MultipartFile file) throws FileNotFoundException, IOException;
	
	List<Product> readlist();
	
	Product readdetail(Integer id);
	
	void update(Integer id, String about, String name, MultipartFile file, double price);
	
	void delete(Integer id);

}
