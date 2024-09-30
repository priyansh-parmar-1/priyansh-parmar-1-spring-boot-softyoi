package com.example.security.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductTest {

	@Test
	void testProductGettersAndSetters() {
		Product product = new Product();
		
		product.setId(1l);
		product.setPname("test");
		product.setPrice(67000.00);
		
		assertEquals(1l, product.getId());
		assertEquals("test",product.getPname());
		assertEquals(67000.00,product.getPrice());		
	}

}
