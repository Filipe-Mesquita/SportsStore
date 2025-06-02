package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.classes.Category;
import com.example.demo.classes.Product;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findTop3ByOrderByIdDesc();
	List<Product> findByCategory(Category category);
}