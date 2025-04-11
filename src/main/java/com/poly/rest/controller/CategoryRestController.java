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

import com.poly.entity.Category;
import com.poly.service.CategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;

	// Lấy danh sách tất cả danh mục
	@GetMapping
	public List<Category> getAll() {
		return categoryService.findAll();
	}

	// Lấy 1 danh mục theo id
	@GetMapping("{id}")
	public Category getOne(@PathVariable("id") String id) {
		return categoryService.findById(id);
	}

	// Thêm mới danh mục
	@PostMapping
	public Category create(@RequestBody Category category) {
		// Tự sinh ID có dạng "C001", "C002", ...
		List<Category> categories = categoryService.findAll();
		int max = 0;
		for (Category c : categories) {
			try {
				String numberPart = c.getId().substring(1); // bỏ chữ "C"
				int num = Integer.parseInt(numberPart);
				if (num > max)
					max = num;
			} catch (Exception e) {
				// Bỏ qua nếu có lỗi parse
			}
		}
		String newId = String.format("C%03d", max + 1); // ví dụ: "C005"
		category.setId(newId);

		return categoryService.create(category);
	}

	// Cập nhật danh mục
	@PutMapping("{id}")
	public Category update(@PathVariable("id") String id, @RequestBody Category category) {
		return categoryService.update(category);
	}

	// Xóa danh mục
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") String id) {
		categoryService.delete(id);
	}
}
