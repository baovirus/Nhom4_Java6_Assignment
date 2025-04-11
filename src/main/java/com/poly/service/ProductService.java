package com.poly.service;

import java.util.List;

import com.poly.entity.Product;

public interface ProductService {

	// Lấy sản phẩm theo loại
	List<Product> getProductsByCategory(String categoryId);

	// Lấy tất cả sản phẩm
	List<Product> findAll();

	// Lấy sản phẩm theo id
	Product findById(Integer id);

	// Tạo mới sản phẩm
	Product create(Product product);

	// Cập nhật sản phẩm
	Product update(Product product);

	// Xoá sản phẩm theo id
	void delete(Integer id);

	List<Product> searchByKeyword(String keyword);
}
