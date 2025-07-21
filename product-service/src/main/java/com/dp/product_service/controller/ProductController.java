package com.dp.product_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dp.product_service.entity.Product;
import com.dp.product_service.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	//To create product
	@PostMapping
	public Product addProduct(@RequestBody Product product) {
	    System.out.println("Received product: " + product);
	    return productRepository.save(product);
	}

	
	//To Retrive/get all product
	@GetMapping
	public List<Product> getAllProduct()
	{
		return productRepository.findAll();
	}
	
	
	//In order to get the specific product by giving id as a input
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Long productId)
	{
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
		return ResponseEntity.ok(product);
	}
}
