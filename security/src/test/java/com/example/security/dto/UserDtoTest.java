package com.example.security.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.security.model.Role;

class UserDtoTest {

	@Test
	void test_userDTO_getters_setters() {
		
		UserDto user = new UserDto();
		user.setName("test");
		user.setAddress("test");
		user.setRole(Role.ADMIN);
		
		assertEquals("test", user.getName());
		assertEquals("test", user.getAddress());
		assertEquals(Role.ADMIN,user.getRole());
	}

}
