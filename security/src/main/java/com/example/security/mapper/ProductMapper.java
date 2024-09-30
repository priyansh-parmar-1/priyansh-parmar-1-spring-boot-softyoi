package com.example.security.mapper;

import org.mapstruct.Mapper;

import com.example.security.dto.ProductDto;
import com.example.security.model.Product;

@Mapper
public interface ProductMapper {

	Product productDtoToUser(ProductDto productDto);

}
