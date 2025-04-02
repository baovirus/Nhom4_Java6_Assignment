package com.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> { // ID là Integer
	// Lấy danh sách sản phẩm theo id của category
	List<Product> findByCategoryId(String categoryId);
}
