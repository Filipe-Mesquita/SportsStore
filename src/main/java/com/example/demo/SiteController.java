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
	
	@GetMapping("/products/category/{categoryId}")
	public String categoryProducts(@PathVariable("categoryId") Long categoryId, Model model) {
	    Category category = service.getCategoryById(categoryId)
	        .orElseThrow(() -> new IllegalArgumentException("Categoria inválida: " + categoryId));

	    List<Product> categoryProducts = service.getProductByCategory(category);

	    model.addAttribute("category", category);
	    model.addAttribute("products", categoryProducts);
	    model.addAttribute("categories", service.getAllCategories());

	    return "category-products";
	}
	
	@GetMapping("/admin")
	public String adminPanel(Model model) {
	    Product product = new Product();
	    product.setCategory(new Category()); // 👈 ESSENCIAL

	    model.addAttribute("product", product);
	    model.addAttribute("category", new Category());
	    model.addAttribute("categories", service.getAllCategories());
	    return "admin-panel";
	}
	
	@PostMapping("/products/new")
	public String saveProduct(@ModelAttribute Product product) {
	    service.save(product);
	    return "redirect:/products";
	}

	@PostMapping("/categories/new")
	public String saveCategory(@ModelAttribute Category category) {
	    service.saveCategory(category);
	    return "redirect:/admin";
	}

	
}
