package com.example.security.mapper;

import org.mapstruct.Mapper;

import com.example.security.dto.UserDto;
import com.example.security.model.User;

@Mapper
public interface UserMapper {

	User userDtoToUser(UserDto userDto);
}
