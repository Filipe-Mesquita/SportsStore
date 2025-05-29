package com.example.demo;

public class Product {
	private Long id;
	private String name;
	private String description;
	private double price;
	private String image;

	public Product(Long id, String name, String description, double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = "/images/fraternitatem_simbolo.png";
	}

	// Getters and setters omitted for brevity
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
