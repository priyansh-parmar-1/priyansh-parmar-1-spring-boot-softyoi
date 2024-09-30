package com.example.security.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String address;

	@Enumerated(EnumType.STRING)
	private Role role;

	public User(String name, String add, Role role) {
		this.name = name;
		this.address = add;
		this.role = role;
	}

}
