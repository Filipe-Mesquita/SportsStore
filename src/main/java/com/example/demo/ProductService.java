package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

	private final List<Product> productList = Arrays.asList(
			new Product(1L, "Laptop", "Powerful gaming laptop", 1200.00),
			new Product(2L, "Headphones", "Noise-cancelling headphones", 200.00),
			new Product(3L, "Smartphone", "Latest Android phone", 900.00));

	public List<Product> getAllProducts() {
		return productList;
	}

	public Optional<Product> getProductById(Long id) {
		return productList.stream().filter(p -> p.getId().equals(id)).findFirst();
	}
}
