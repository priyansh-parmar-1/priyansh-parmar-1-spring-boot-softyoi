package com.example.security.controller;

import com.example.security.dto.UserDto;
import com.example.security.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
        	return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
        }catch(Exception e) {
        	return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto user) {
    	try {
        	return new ResponseEntity<>(userService.save(user),HttpStatus.CREATED);
        }catch(Exception e) {
        	return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
    	try {
        	return new ResponseEntity<>(userService.deleteById(id),HttpStatus.CREATED);
        }catch(Exception e) {
        	return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

}
