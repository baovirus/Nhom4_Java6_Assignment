package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.entity.Product;
import com.poly.repository.ProductRepository;
import com.poly.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getProductsByCategory(String categoryId) {
		return productRepository.findByCategoryId(categoryId);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return productRepository.findById(id).orElse(null);
	}

	public List<Product> searchByKeyword(String keyword) {
		return productRepository.findByNameContainingIgnoreCase(keyword);
	}

	@Override
	public Product create(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product update(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void delete(Integer id) {
		productRepository.deleteById(id);
	}
}
