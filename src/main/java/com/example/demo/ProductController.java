package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "index";
	}

	@GetMapping("/products")
	public String allProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@GetMapping("/products/{id}")
	public String productDetails(@PathVariable("id") Long id, Model model) {
		Product product = productService.getProductById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
		model.addAttribute("product", product);
		return "product-detail";
	}
}
