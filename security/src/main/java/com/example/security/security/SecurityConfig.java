package com.example.security.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.security.jwt.JwtRequestFilter;
import com.example.security.swaggerSecurity.SwaggerBasicAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService userDetailsService() {
		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("mypassword"))
				.roles("ADMIN").build();

		UserDetails user = User.builder().username("user").password(passwordEncoder().encode("mypassword"))
				.roles("USER").build();

		UserDetails seller = User.builder().username("seller").password(passwordEncoder().encode("mypassword"))
				.roles("SELLER").build();

		return new InMemoryUserDetailsManager(admin, user, seller);
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, JwtRequestFilter jwtRequestFilter) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/authenticate").permitAll()
						.requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
						.anyRequest().authenticated())
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	FilterRegistrationBean<SwaggerBasicAuthFilter> swaggerBasicAuthFilterRegistration() {
		FilterRegistrationBean<SwaggerBasicAuthFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new SwaggerBasicAuthFilter());
		registrationBean.addUrlPatterns("/swagger/*", "/v2/api-docs", "/swagger-ui.html");
		registrationBean.setName("SwaggerBasicAuthFilter");
		return registrationBean;
	}

}
