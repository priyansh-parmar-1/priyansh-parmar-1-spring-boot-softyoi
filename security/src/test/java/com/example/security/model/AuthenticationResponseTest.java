package com.example.security.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AuthenticationResponseTest {

	@Test
	void test_authentication_response() {

		AuthenticationResponse authRes = new AuthenticationResponse("test");
		assertEquals("test", authRes.getJwt());
	}

}
