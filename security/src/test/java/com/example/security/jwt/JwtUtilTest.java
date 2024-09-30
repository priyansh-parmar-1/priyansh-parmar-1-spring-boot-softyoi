package com.example.security.jwt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

@ExtendWith(MockitoExtension.class)
class JwtUtilTest {

	private JwtUtil jwtUtil;

	@Mock
	UserDetails userDetails;

	private String testToken;

	@BeforeEach
	void setup() {

		jwtUtil = new JwtUtil();

		when(userDetails.getUsername()).thenReturn("testuser");

		testToken = jwtUtil.generateToken(userDetails);
	}

	@DisplayName("extract username")
	@Test
	void test_extract_username() {

		String username = jwtUtil.extractUsername(testToken);

		assertEquals("testuser", username);
	}
	
	@DisplayName("extract expiration date")
	@Test
	void test_extract_expiration() {

		Date expiry = jwtUtil.extractExpiration(testToken);

		assertNotNull(expiry);
		assertTrue(expiry.after(new Date()));
	}
	
	@DisplayName("is token expired")
	@Test
	void test_is_token_expired() {

		boolean isexpired = jwtUtil.extractExpiration(testToken).before(new Date());

		assertFalse(isexpired);
	}

	@DisplayName("token generation")
	@Test
	void test_generate_token() {
		
		String token = jwtUtil.generateToken(userDetails);
		
		assertNotNull(token);
		assertEquals("testuser", jwtUtil.extractUsername(token));
		
	}
	
	@DisplayName("is token valid")
	@Test
	void test_is_valid_token() {
		
		Boolean isValid = jwtUtil.validateToken(testToken, userDetails);
		
		assertTrue(isValid);
		
	}
	
}
