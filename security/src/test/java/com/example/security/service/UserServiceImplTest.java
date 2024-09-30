package com.example.security.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.security.dto.UserDto;
import com.example.security.mapper.UserMapper;
import com.example.security.model.Role;
import com.example.security.model.User;
import com.example.security.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@Mock
	UserRepository userRepo;

	@Mock
	UserMapper userMapper;

	@InjectMocks
	UserServiceImpl userService;

	@DisplayName("find all users")
	@Test
	void test_findAll() {

		List<User> mockUsers = new ArrayList<>();
		mockUsers.add(new User(1L, "test1", "testadd", Role.USER));
		mockUsers.add(new User(2L, "test2", "testadd", Role.SELLER));

		when(userRepo.findAll()).thenReturn(mockUsers);

		List<User> result = userService.findAll();

		assertEquals(2, mockUsers.size());
		assertEquals("test1", result.get(0).getName());
		assertEquals("test2", result.get(1).getName());

		verify(userRepo, times(1)).findAll();
	}

	@DisplayName("save user")
	@Test
	void test_save() {

		User mockUser = new User(1L, "test", "testadd", Role.USER);

		UserDto mockUserDto = new UserDto();
		mockUserDto.setName("test");
		mockUserDto.setAddress("test");
		mockUserDto.setRole(Role.USER);

		when(userMapper.userDtoToUser(mockUserDto)).thenReturn(mockUser);
		when(userRepo.save(mockUser)).thenReturn(mockUser);

		User result = userService.save(mockUserDto);

		assertEquals(mockUser, result);

		verify(userMapper, times(1)).userDtoToUser(mockUserDto);
		verify(userRepo, times(1)).save(mockUser);
	}

	@DisplayName("delete by id success")
	@Test
	void test_delete_by_id_success() throws Exception {

		Long id = 1L;
		User mockUser = new User(id, "test", "testadd", Role.USER);

		Optional<User> mockOptUser = Optional.of(mockUser);

		when(userRepo.findById(id)).thenReturn(mockOptUser);

		User result = userService.deleteById(id);

		assertEquals(mockUser, result);

		verify(userRepo, times(1)).deleteById(id);
		verify(userRepo, times(1)).findById(id);

	}

	@DisplayName("delete by id failure")
	@Test
	void test_delete_by_id_failure() throws Exception {

		Long id = 1L;

		Optional<User> mockOptUser = Optional.empty();

		Exception result = assertThrows(RuntimeException.class, () -> userService.deleteById(id));

		assertEquals("User not found", result.getMessage());

		verify(userRepo, never()).deleteById(id);
	}
}
