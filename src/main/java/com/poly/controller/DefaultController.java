package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.entity.Category;
import com.poly.entity.Product;
import com.poly.service.CategoryService;
import com.poly.service.ProductService;

@Controller
public class DefaultController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;

	@RequestMapping("/")
	public String index(Model model) {

		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);

		List<Product> products = productService.findAll();
		model.addAttribute("products", products);
		return ("product/list");
	}

	@RequestMapping("/template")
	public String template() {
		return ("template");
	}

}
