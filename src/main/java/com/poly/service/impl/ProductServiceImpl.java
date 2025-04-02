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
	private ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> getProductsByCategory(String categoryId) {
		return productRepository.findByCategoryId(categoryId);
	}

	@Override
	public Product findById(Integer id) {
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void delete(Integer id) {
		productRepository.deleteById(id);
	}
}
