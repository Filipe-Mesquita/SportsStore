package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

	private final List<Product> productList = Arrays.asList(
			new Product(1L, "Bola de Futebol", "Redonda", 1200.00, "Futebol", 10),
			new Product(2L, "Taco de Basebol", "BONK", 200.00, "Basebol", 20 ),
			new Product(3L, "Bola de Basket", "Lebron", 900.00, "Basquetebol", 30));
			
	

	public List<Product> getAllProducts() {
		return productList;
	}

	public Optional<Product> getProductById(Long id) {
		return productList.stream().filter(p -> p.getId().equals(id)).findFirst();
	}
	
	public List<String> getAllCategories() {
		return productList.stream()
				.map(Product::getCategory)
				.distinct()
				.collect(Collectors.toList());
	}
	
	public List<Product> getProductByCategory(String category) {
		return productList.stream()
				.filter(p -> p.getCategory().equalsIgnoreCase(category))
				.collect(Collectors.toList());
	}
	

	
	
}
