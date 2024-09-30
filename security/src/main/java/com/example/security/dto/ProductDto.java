package com.example.security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ProductDto {

	private String pname;
    private Double price;
}
