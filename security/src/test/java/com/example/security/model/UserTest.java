package com.example.security.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	void test_user_getters_setters() {
		
		Role role = Role.USER;
		
		User user = new User();
		user.setId(1L);
		user.setAddress("testAdd");
		user.setName("test");
		user.setRole(role);
		
		
		assertEquals(1L, user.getId());
		assertEquals("testAdd", user.getAddress());
		assertEquals("test", user.getName());
		assertEquals(Role.USER, user.getRole());
		
	}

}
