package com.poly.service;

import java.util.List;

import com.poly.entity.Product;

public interface ProductService {

	List<Product> getProductsByCategory(String categoryId);

	// DAO
	List<Product> findAll();

	Product findById(Integer id);

	Product save(Product product);

	void delete(Integer id);
}
