package com.example.security.service;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.dto.UserDto;
import com.example.security.mapper.UserMapper;
import com.example.security.model.User;
import com.example.security.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	UserMapper userMapper = Mappers.getMapper(UserMapper.class);
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(UserDto userDto) {
		User user = userMapper.userDtoToUser(userDto);
		return userRepository.save(user);
	}

	@Override
	public User deleteById(Long id) throws Exception {
		Optional<User> userOpt = userRepository.findById(id);
		if(userOpt.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		User user = userOpt.get();
		userRepository.deleteById(id);
		return user;
	}
}
