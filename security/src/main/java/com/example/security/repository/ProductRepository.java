package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
