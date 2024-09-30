package com.example.security.service;

import java.util.List;

import com.example.security.dto.UserDto;
import com.example.security.model.User;

public interface UserService {

	public List<User> findAll();
	
	public User save(UserDto userDto);
	
	public User deleteById(Long id) throws Exception;
}
