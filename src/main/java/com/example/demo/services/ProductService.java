package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.classes.Category;
import com.example.demo.classes.Product;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repo;
    private final CategoryRepository categoryRepo;

    public ProductService(ProductRepository repo, CategoryRepository categoryRepo) {
        this.repo = repo;
        this.categoryRepo = categoryRepo;
    }

    // Get all products
    public List<Product> list() {
        return repo.findAll();
    }

    // Save a product
    public Product save(Product p) {
        return repo.save(p);
    }
    
    public void saveCategory(Category c) {
        categoryRepo.save(c);
    }
    
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepo.findById(id);
    }
    // Get latest 3 products (ordered by ID descending)
    public List<Product> getLatestProducts() {
        return repo.findTop3ByOrderByIdDesc();
    }

    // Get product by ID
    public Optional<Product> getProductById(Long id) {
        return repo.findById(id);
    }

    // Get products by category name
    public List<Product> getProductByCategory(Category category) {
        return repo.findByCategory(category);
    }

    // Get all categories (useful for frontend dropdowns, filters, etc.)
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
