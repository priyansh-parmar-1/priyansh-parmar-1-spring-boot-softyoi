package com.example.security.service;

import java.util.List;

import com.example.security.dto.ProductDto;
import com.example.security.model.Product;

public interface ProductService {

	public List<Product> findAll();
	
	public Product save(ProductDto product);
	
	public Product deleteById(Long id) throws Exception;
}
