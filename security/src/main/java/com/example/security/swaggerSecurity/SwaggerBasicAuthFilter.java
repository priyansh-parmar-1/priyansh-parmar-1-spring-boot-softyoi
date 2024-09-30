package com.example.security.swaggerSecurity;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class SwaggerBasicAuthFilter extends OncePerRequestFilter {
	
	
	private String swaggerUsername = "priyansh";
	
	private String swaggerPassword = "abc";
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String requestUri = request.getRequestURI();

		if (isRestrictedUrl(requestUri)) {
			String authHeader = request.getHeader("Authorization");

			if (authHeader != null && authHeader.startsWith("Basic ")) {
				String[] credentials = extractAndDecodeHeader(authHeader);

				if (isAuthorized(credentials[0], credentials[1])) {
					chain.doFilter(request, response);
					return;
				}
			}

			response.setHeader("WWW-Authenticate", "Basic realm=\"Swagger\"");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		chain.doFilter(request, response);
	}

	private boolean isRestrictedUrl(String requestUri) {
		return requestUri.startsWith("/swagger") || requestUri.startsWith("/api-docs")
				|| requestUri.startsWith("/swagger-ui.html");
	}

	private boolean isAuthorized(String username, String password) {
		logger.info(swaggerUsername + ":" + swaggerPassword);
		return username.equals(swaggerUsername) && password.equals(swaggerPassword);
	}

	private String[] extractAndDecodeHeader(String header) throws IOException {
		byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
		byte[] decoded;
		try {
			decoded = Base64.getDecoder().decode(base64Token);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Failed to decode basic authentication token");
		}

		String token = new String(decoded, StandardCharsets.UTF_8);

		int delim = token.indexOf(":");

		if (delim == -1) {
			throw new RuntimeException("Invalid basic authentication token");
		}

		return new String[] { token.substring(0, delim), token.substring(delim + 1) };
	}
}