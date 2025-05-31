package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@Controller
public class SiteController {

	@Autowired
	private ProductService productService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("latestProducts", productService.getLatestProducts());
		model.addAttribute("categories", productService.getAllCategories());
		return "index";
	}

	@GetMapping("/products")
	public String allProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		model.addAttribute("categories", productService.getAllCategories());
		return "products";
	}

	@GetMapping("/products/{id}")
	public String productDetails(@PathVariable("id") Long id, Model model) {
		Product product = productService.getProductById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
		model.addAttribute("product", product);
		model.addAttribute("categories", productService.getAllCategories());
		return "product-detail";
	}
	
	@GetMapping("/products/category/{category}")
	public String categoryProducts(@PathVariable("category") String category, Model model) {
		List <Product> categoryProducts = productService.getProductByCategory(category);
		model.addAttribute("category", category);
		model.addAttribute("products", categoryProducts);
		model.addAttribute("categories", productService.getAllCategories());
		return "category-products";
		
		
	}

	
}
