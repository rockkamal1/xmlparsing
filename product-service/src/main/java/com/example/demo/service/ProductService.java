package com.example.demo.service;

import com.example.demo.dto.ProductResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.ProductRepository;
import com.example.demo.dto.ProductRequest;
import com.example.demo.model.Product;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

public class ProductService {

	private final ProductRepository productRepository;

	public void createProduct(ProductRequest productRequest) {

		Product product = Product.builder()
				        .name(productRequest.getName())
		                .description(productRequest.getDescription())
		                .price(productRequest.getPrice())
		                .build();
		productRepository.save(product);
		log.info("Product {} is saved"+product.getId());
	}

	public List<ProductResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(this::mapToProductresponse).collect(Collectors.toList());


	}

	private ProductResponse mapToProductresponse(Product product) {
		return ProductResponse.builder()
				.id(String.valueOf(product.getId()))
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}

}
