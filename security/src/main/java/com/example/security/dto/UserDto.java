package com.example.security.dto;

import com.example.security.model.Role;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class UserDto {

	private String name;
	
	private String address;

	private Role role;
}
