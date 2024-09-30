package com.example.security.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AuthenticationRequestTest {

	@Test
	void test_authentication_request_getter_setter() {
		
		AuthenticationRequest auth = new AuthenticationRequest();
		
		auth.setUsername("test");
		auth.setPassword("testPass");

		assertEquals("test",auth.getUsername());
		assertEquals("testPass",auth.getPassword());
	}

}
