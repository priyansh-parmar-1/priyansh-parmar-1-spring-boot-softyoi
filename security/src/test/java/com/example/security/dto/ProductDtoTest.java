package com.example.security.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductDtoTest {

	@Test
	void test_productDTO_getter_setters() {

		ProductDto prod = new ProductDto();

		prod.setPname("test");
		prod.setPrice(45000.00);

		assertEquals("test", prod.getPname());
		assertEquals(45000.00, prod.getPrice());

	}

}
