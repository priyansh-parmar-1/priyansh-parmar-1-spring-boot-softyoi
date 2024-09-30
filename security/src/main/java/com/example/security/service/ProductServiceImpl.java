package com.example.security.service;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.dto.ProductDto;
import com.example.security.mapper.ProductMapper;
import com.example.security.mapper.UserMapper;
import com.example.security.model.Product;
import com.example.security.model.User;
import com.example.security.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    
    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(ProductDto productDto) {
    	Product product = productMapper.productDtoToUser(productDto);
    	productRepository.save(product);
		return product ;
    }

    @Override
	public Product deleteById(Long id) throws Exception {
		Optional<Product> productOpt = productRepository.findById(id);
		if(productOpt.isEmpty()) {
			throw new RuntimeException("product not found");
		}
		Product product = productOpt.get();
		productRepository.deleteById(id);
		return product;
	}
}
