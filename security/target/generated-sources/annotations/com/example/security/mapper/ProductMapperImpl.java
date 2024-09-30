package com.example.security.mapper;

import com.example.security.dto.ProductDto;
import com.example.security.model.Product;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-30T11:10:50+0530",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product productDtoToUser(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        return product;
    }
}
