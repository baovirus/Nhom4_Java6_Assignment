package com.poly.service;

import java.util.List;

import com.poly.entity.Category;

public interface CategoryService {

	// DAO
	List<Category> findAll();

	Category findById(String id);

	Category save(Category category);

	Category create(Category category);

	Category update(Category category);

	void delete(String id);
}
