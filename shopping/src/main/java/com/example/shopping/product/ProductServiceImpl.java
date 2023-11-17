package com.example.shopping.product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class ProductServiceImpl implements ProductService {

	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Value("youjinbucket")
	private String bucketName;
	
	@Override
	public void create(Product product, MultipartFile file) throws FileNotFoundException, IOException {
		
		File pimg = new File(file.getOriginalFilename());
		
		//aws s3 mulpartfile을 막바로 올리수 없게 되어있다 
		//따라서 파일을 일단 저장한 후에 그 파일을 aws로 올리고 삭제한다. 
		
		try (FileOutputStream fos = new FileOutputStream(pimg)) {
			fos.write(file.getBytes());
		}
		
		//역시 보안등의 이유로 uuid 를 사용해도 좋지만 이번엔 다른 방법을 사용해 보자 
		
		String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		amazonS3.putObject(new PutObjectRequest(bucketName, filename, pimg));
		
		
		product.setCreateDate(LocalDateTime.now());
		product.setPimg(filename);
		productRepository.save(product);
	}

	@Override
	public List<Product> readlist() {
		
		return productRepository.findAll();
	}

	@Override
	public Product readdetail(Integer id) {
		
		Optional<Product> op = productRepository.findById(id);
		Product product = op.get();
		
		return product;
	}

	

	@Override
	public void delete(Integer id) {
		Optional<Product> op = productRepository.findById(id);
		Product product = op.get();
		
		productRepository.delete(product);
	}

	@Override
	public void update(Integer id, String about, String name, MultipartFile file, double price ) throws FileNotFoundException, IOException {
		Optional<Product> op = productRepository.findById(id);
		Product product = op.get();
		
		
		File pimg = new File(file.getOriginalFilename());
		
		//aws s3 mulpartfile을 막바로 올리수 없게 되어있다 
		//따라서 파일을 일단 저장한 후에 그 파일을 aws로 올리고 삭제한다. 
		
		try (FileOutputStream fos = new FileOutputStream(pimg)) {
			fos.write(file.getBytes());
		}
		
		//역시 보안등의 이유로 uuid 를 사용해도 좋지만 이번엔 다른 방법을 사용해 보자 
		
		String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		amazonS3.putObject(new PutObjectRequest(bucketName, filename, pimg));
		
		
		
		product.setAbout(about);
		product.setName(name);
		product.setPimg(filename);
		product.setPrice(price);
		product.setCreateDate(LocalDateTime.now());
		
		productRepository.save(product);
		
	}

}
