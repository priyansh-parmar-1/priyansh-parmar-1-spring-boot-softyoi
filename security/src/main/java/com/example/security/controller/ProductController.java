package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.dto.ProductDto;
import com.example.security.service.ProductServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('SELLER')")
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
    	try {
        	return new ResponseEntity<>(productService.findAll(),HttpStatus.OK);
        }catch(Exception e) {
        	return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER')")
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDto product) {
    	try {
        	return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
        }catch(Exception e) {
        	return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
    	try {
        	return new ResponseEntity<>(productService.deleteById(id),HttpStatus.CREATED);
        }catch(Exception e) {
        	return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
