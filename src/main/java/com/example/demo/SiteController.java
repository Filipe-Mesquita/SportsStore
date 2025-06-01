package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Product;
import com.example.demo.ProductService;


@Controller
public class SiteController {

	@Autowired
	private ProductService service;
	
	
	public SiteController(ProductService service) {
        this.service = service;
    }


	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("latestProducts", service.getLatestProducts());
		model.addAttribute("categories", service.getAllCategories());
		return "index";
	}

	@GetMapping("/products")
	public String allProducts(Model model) {
		model.addAttribute("products", service.list());
		model.addAttribute("categories", service.getAllCategories());
		return "products";
	}

	@GetMapping("/products/{id}")
	public String productDetails(@PathVariable("id") Long id, Model model) {
		Product product = service.getProductById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
		model.addAttribute("product", product);
		model.addAttribute("categories",service.getAllCategories());
		return "product-detail";
	}
	
	@GetMapping("/products/category/{category}")
	public String categoryProducts(@PathVariable("category") String category, Model model) {
		List <Product> categoryProducts = service.getProductByCategory(category);
		model.addAttribute("category", category);
		model.addAttribute("products", categoryProducts);
		model.addAttribute("categories", service.getAllCategories());
		return "category-products";
		
		
	}

	
}
