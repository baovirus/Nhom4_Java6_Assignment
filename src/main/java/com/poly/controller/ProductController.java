package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.entity.Category;
import com.poly.entity.Product;
import com.poly.service.AccountService;
import com.poly.service.CategoryService;
import com.poly.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private AccountService accountService;

	@RequestMapping("/list")
	public String index(Model model) {

		List<Category> categories = categoryService.findAll();
		List<Product> products = productService.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("products", products);
		return ("product/list");
	}

	@RequestMapping("/{id}")
	public String ProductDetail(@PathVariable Integer id, Model model) {

		List<Category> categories = categoryService.findAll();
		Product product = productService.findById(id);
		// Lấy danh sách sản phẩm cùng danh mục
		List<Product> relatedProducts = productService.getProductsByCategory(product.getCategory().getId());
		model.addAttribute("categories", categories);
		model.addAttribute("product", product);
		model.addAttribute("relatedProducts", relatedProducts);
		return ("product/detail");
	}

	@RequestMapping("/category/{id}")
	public String FindProductByCategory(@PathVariable String id, Model model) {

		List<Category> categories = categoryService.findAll();
		List<Product> products = productService.getProductsByCategory(id);
		model.addAttribute("categories", categories);
		model.addAttribute("products", products);
		return ("product/list");
	}
}
