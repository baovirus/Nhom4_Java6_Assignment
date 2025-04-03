package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.entity.Category;
import com.poly.service.CategoryService;

@Controller
public class ShoppingCartController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/my-cart")
	public String my_cart(Model model) {

		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);

		return ("cart/my-cart");
	}

	@RequestMapping("/my-order")
	public String my_order() {
		return ("order/my-order");
	}

}
