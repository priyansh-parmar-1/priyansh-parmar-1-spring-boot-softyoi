package com.example.security.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.example.security.dto.ProductDto;
import com.example.security.mapper.ProductMapper;
import com.example.security.model.Product;
import com.example.security.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

	@Mock
	ProductRepository productRepo;

	@Mock
	ProductMapper productMapper;

	@InjectMocks
	ProductServiceImpl productService;

	@DisplayName("findAll products")
	@Test
	void test_findAll() {

		List<Product> mockProducts = new ArrayList<>();
		mockProducts.add(new Product(1L, "test1", 0.00));
		mockProducts.add(new Product(2L, "test2", 0.00));

		when(productRepo.findAll()).thenReturn(mockProducts);

		List<Product> result = productService.findAll();

		assertEquals(2, result.size());
		assertEquals("test1", result.get(0).getPname());
		assertEquals("test2", result.get(1).getPname());

		// verify that it is called only once
		verify(productRepo, times(1)).findAll();

	}

	@DisplayName("save a product")
	@Test
	void test_save() {

		ProductDto mockProdDto = new ProductDto();
		mockProdDto.setPname("test");
		mockProdDto.setPrice(0.0);

		Product mockProduct = new Product(1L, "test", 0.0);

		when(productMapper.productDtoToUser(mockProdDto)).thenReturn(mockProduct);
		when(productRepo.save(mockProduct)).thenReturn(mockProduct);

		Product result = productService.save(mockProdDto);

		assertEquals(mockProduct, result);

		verify(productMapper, times(1)).productDtoToUser(mockProdDto);
		verify(productRepo, times(1)).save(mockProduct);

	}

	@DisplayName("delete by id Success")
	@Test
	void test_delete_by_id_successfull() throws Exception {

		Long prodId = 1L;
		Product mockProduct = new Product(prodId, "test", 0.0);
		Optional<Product> mockOptional = Optional.of(mockProduct);
		when(productRepo.findById(prodId)).thenReturn(mockOptional);

		Product res = productService.deleteById(prodId);

		assertEquals(mockProduct, res);

		verify(productRepo, times(1)).findById(prodId);
		verify(productRepo, times(1)).deleteById(prodId);
	}
	
	@DisplayName("delete by id Failure")
	@Test
	void test_delete_by_id_unsuccessfull() throws Exception {

		Long prodId = 1L;
		
		Optional<Product> mockOptional = Optional.empty();
		
		when(productRepo.findById(prodId)).thenReturn(mockOptional);

		Exception exception =  assertThrows(RuntimeException.class, () -> productService.deleteById(prodId));
		
		assertEquals("product not found", exception.getMessage());
		
		verify(productRepo,never()).deleteById(prodId);
		
	}

}
