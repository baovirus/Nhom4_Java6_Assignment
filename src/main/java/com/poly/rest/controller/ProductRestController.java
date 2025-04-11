package com.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.Product;
import com.poly.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

	@Autowired
	private ProductService productService;

	// GET all products
	@GetMapping()
	public List<Product> getAll() {
		return productService.findAll();
	}

	// GET product by ID
	@GetMapping("{id}")
	public Product ProductDetail(@PathVariable Integer id) {
		return productService.findById(id);
	}

	// POST new product
	@PostMapping()
	public Product create(@RequestBody Product product) {
		return productService.create(product);
	}

	// PUT update product
	@PutMapping("{id}")
	public Product update(@PathVariable Integer id, @RequestBody Product product) {
		return productService.update(product);
	}

	// DELETE product by ID
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		productService.delete(id);
	}
}
